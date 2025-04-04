package com.service;

import com.dto.request.RegisterRequest;
import com.dto.request.UpdateUserRequest;
import com.dto.request.UserRequest;
import com.dto.response.*;
import com.dto.response.details.ExpertDetailsResponse;
import com.entity.*;
import com.exception.custom.*;
import com.helper.CourseServiceHelper;
import com.helper.ExpertServiceHelper;
import com.helper.OrderServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.*;
import com.service.auth.JwtService;
import com.util.BuildResponse;
import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import com.util.enums.RoleNameEnum;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OTPService otpService;
    private final OTPRepository otpRepository;
    private final FileService fileService;
    private final UserServiceHelper userServiceHelper;
    private final ExpertRepository expertRepository;
    private final OrderRepository orderRepository;
    private final OrderServiceHelper orderServiceHelper;
    private final ExpertServiceHelper expertServiceHelper;
    private final CourseServiceHelper courseServiceHelper;

    public ApiResponse<Void> sendRegisterRequest(RegisterRequest registerRequest) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndAccountType(registerRequest.getEmail(), AccountTypeEnum.CREDENTIALS);
        if (optionalUserEntity.isPresent()) {
            UserEntity existedUserEntity = optionalUserEntity.get();
            if (Boolean.TRUE.equals(existedUserEntity.getActive())) {
                throw new UserException("Email này đã được sử dụng!");
            } else {
                OTPEntity otpEntity = existedUserEntity.getOtp();
                existedUserEntity.setOtp(null);
                UserEntity savedUserEntity = userRepository.save(existedUserEntity);
                otpRepository.delete(otpEntity);
                userRepository.delete(savedUserEntity);
            }
        }

        RoleEntity roleEntity = roleRepository.findByRoleName(RoleNameEnum.USER)
                .orElseThrow(() -> new RoleException("Role not found!"));
        UserEntity userEntity = modelMapper.map(registerRequest, UserEntity.class);
        userEntity.setAccountType(AccountTypeEnum.CREDENTIALS);
        userEntity.setActive(false);
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setRole(roleEntity);
        UserEntity savedUser = userRepository.save(userEntity);
        otpService.generateOTP(savedUser, "Yêu cầu xác thực email!");
        return BuildResponse.buildApiResponse(
                200,
                "Vui lòng kiểm tra email của ban!",
                null,
                null
        );
    }

    public UserResponse getUserById(Long id) {
        String email = JwtService.extractUsernameFromToken()
                .orElseThrow(() -> new NotFoundException("Email không tìm thấy"));

        String accountType = JwtService.extractAccountTypeFromToken()
                .orElseThrow(() -> new NotFoundException("Account type không tìm thấy"));

        UserEntity loginUser = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                .orElseThrow(() -> new NotFoundException("Vui lòng đăng nhập"));

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        if (!loginUser.getRole().getRoleName().equals(RoleNameEnum.ADMIN) && Boolean.TRUE.equals(user.getLocked())) {
            throw new NotFoundException("Không tìm thấy người dùng");
        }

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setRoleName(user.getRole().getRoleName());
        return userResponse;

    }

    public PageDetailsResponse<List<UserResponse>> getUserWithFilter(
            Pageable pageable, Specification<UserEntity> specification) {

        Page<UserEntity> page = userRepository.findAll(specification, pageable);
        List<UserResponse> userResponses = page.getContent()
                .stream().map(userEntity -> {
                    UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
                    userResponse.setRoleName(userEntity.getRole().getRoleName());
                    return userResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                userResponses
        );
    }

    public PageDetailsResponse<List<UserResponse>> getUserByCourse(
            Pageable pageable, Specification<UserEntity> specification, Long courseId) {

        // Tạo Specification để lọc User có khóa học cụ thể
        Specification<UserEntity> courseSpecification = (root, query, criteriaBuilder) -> {
            // Join users với courses
            Join<UserEntity, CourseEntity> courseJoin = root.join("courses");
            return criteriaBuilder.equal(courseJoin.get("courseId"), courseId);
        };

        // Kết hợp Specification tìm kiếm + lọc khóa học
        Specification<UserEntity> finalSpecification = specification.and(courseSpecification);

        // Lấy danh sách UserEntity theo phân trang + Specification
        Page<UserEntity> page = userRepository.findAll(finalSpecification, pageable);

        List<UserResponse> userResponses = page.getContent()
                .stream()
                .map(userEntity -> {
                    UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
                    userResponse.setRoleName(userEntity.getRole().getRoleName());
                    return userResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                userResponses
        );
    }

    public boolean lockUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
        if (user.getRole().getRoleName().equals(RoleNameEnum.ADMIN)) {
            throw new UserException("Không được khóa ADMIN");
        }
        if (Boolean.TRUE.equals(user.getLocked())) {
            user.setLocked(false);
        } else {
            user.setLocked(true);
        }
        return userRepository.save(user).getLocked();
    }

    public UserResponse createUser(UserRequest request) {
        try {
            if (request.getUserId() != null) {
                throw new UserException("Không được để id");
            }
            // Tìm role
            RoleEntity roleEntity = roleRepository.findByRoleName(request.getRoleName())
                    .orElseThrow(() -> new RoleException("Role không tìm thấy"));

            // Kiểm tra email đã tồn tại hay chưa
            if (userRepository.existsByEmailAndAccountType(request.getEmail(), AccountTypeEnum.CREDENTIALS)) {
                throw new UserException("Email đã được sử dụng");
            }

            // Tạo UserEntity
            UserEntity user = modelMapper.map(request, UserEntity.class);
            user.setRole(roleEntity);
            user.setAccountType(AccountTypeEnum.CREDENTIALS);
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            // Lưu user trước
            UserEntity savedUser = userRepository.save(user);

// Nếu role là EXPERT, tạo ExpertEntity và lưu tiếp
            if (RoleNameEnum.EXPERT.equals(request.getRoleName())) {
                ExpertEntity expert = ExpertEntity.builder()
                        .user(savedUser)  // ✅ Dùng savedUser đã có ID
                        .job(request.getJob())
                        .achievement(request.getAchievement())
                        .description(request.getDescription())
                        .yearOfExperience(request.getYearOfExperience())
                        .build();

                expert = expertRepository.save(expert); // ✅ Lưu expert vào DB
                savedUser.setExpert(expert);
            }

            UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);

            return userResponse;
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi ra console để debug
            throw new RuntimeException("Lỗi khi tạo người dùng: " + e.getMessage());
        }

    }

    public UserResponse updateUser(UserRequest request) {
        if (request.getUserId() == null) {
            throw new UserException("Cập nhật người dùng phải có id");
        }
        RoleEntity roleEntity = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new RoleException("Role không tìm thấy"));
        if (userRepository.existsByEmailAndAccountTypeAndUserIdNot(request.getEmail(), AccountTypeEnum.CREDENTIALS, request.getUserId())) {
            throw new UserException("Email đã được sử dụng");
        }
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserException("Không tìm thấy người dùng"));
        user.setRole(roleEntity);
        user.setDob(request.getDob());
        user.setAvatar(request.getAvatar());
        user.setGender(request.getGender());
        user.setFullname(request.getFullname());
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public UserResponse updateUserProfile(UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(updateUserRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("ID không tồn tại!"));
        userEntity.setFullname(updateUserRequest.getFullname());
        userEntity.setDob(updateUserRequest.getDob());
        userEntity.setGender(updateUserRequest.getGender());
        return modelMapper.map(userRepository.save(userEntity), UserResponse.class);
    }

    public UserResponse getUserProFile() {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Người dùng không tồn tại!");
        }
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        userResponse.setRoleName(userEntity.getRole().getRoleName());
        return userResponse;
    }

    public UserResponse updateAvatar(MultipartFile file, String folder) throws URISyntaxException, IOException {
        if (file == null || file.isEmpty()) {
            throw new StorageException("File bị rỗng");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
        boolean isValid = allowedExtensions.stream().anyMatch(item -> {
            assert fileName != null;
            return fileName.toLowerCase().endsWith(item);
        });
        if (!isValid) {
            throw new StorageException("Bạn phải truyền file có định dạng:  " + allowedExtensions.toString());
        }
        ApiResponse<String> fileResponse = fileService.uploadFile(file, folder);
        if (fileResponse.getStatus() == 200) {
            UserEntity userEntity = userServiceHelper.extractUserFromToken();
            if (userEntity == null) {
                throw new UserException("Người dùng không tồn tại!");
            }
            userEntity.setAvatar(fileResponse.getData());
            return modelMapper.map(userRepository.save(userEntity), UserResponse.class);
        } else {
            throw new StorageException("Không tìm thấy ảnh");
        }
    }

    public UserResponse updateAvatarByAdmin(MultipartFile file, String folder) throws URISyntaxException, IOException {
        if (file == null || file.isEmpty()) {
            throw new StorageException("File bị rỗng");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
        boolean isValid = allowedExtensions.stream().anyMatch(item -> {
            assert fileName != null;
            return fileName.toLowerCase().endsWith(item);
        });
        if (!isValid) {
            throw new StorageException("Bạn phải truyền file có định dạng:  " + allowedExtensions.toString());
        }
        ApiResponse<String> fileResponse = fileService.uploadFile(file, folder);
        if (fileResponse.getStatus() == 200) {
            UserEntity userEntity = new UserEntity();
            userEntity.setAvatar(fileResponse.getData());
            return modelMapper.map(userRepository.save(userEntity), UserResponse.class);
        } else {
            throw new StorageException("Không tìm thấy ảnh");
        }
    }

    public GenderCountResponse genderCount() {
        Long countAllGender = this.userRepository.count();
        Long countMale = this.userRepository.countByGender(GenderEnum.MALE);
        Long countFemale = this.userRepository.countByGender(GenderEnum.FEMALE);
        Long countUnknown = countAllGender - countMale - countFemale;
        return new GenderCountResponse(countMale, countFemale, countUnknown);
    }

    public Long getUsersByAgeRange(int minAge, int maxAge) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minus(Period.ofYears(maxAge));
        LocalDate endDate = today.minus(Period.ofYears(minAge));
        Instant startInstant = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endInstant = endDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return userRepository.countByDobBetween(startInstant, endInstant);
    }

    public Map<String, Long> getUserByAge() {
        Map<String, Long> allAge = new HashMap<>();
        Long allUser = this.userRepository.count();
        Long countAge6To12 = getUsersByAgeRange(6, 12);
        Long countAge13To19 = getUsersByAgeRange(13, 19);
        Long countAge20To39 = getUsersByAgeRange(20, 39);
        Long countAge40To59 = getUsersByAgeRange(40, 59);
        Long countAge60To110 = getUsersByAgeRange(60, 110);
        Long countAgeUnknown = allUser - countAge6To12 - countAge13To19 - countAge20To39 - countAge40To59 - countAge60To110;

        allAge.put("6-12", countAge6To12);
        allAge.put("13-19", countAge13To19);
        allAge.put("20-39", countAge20To39);
        allAge.put("40-59", countAge40To59);
        allAge.put("60-110", countAge60To110);
        allAge.put("Unknown", countAgeUnknown);

        return allAge;
    }

    public List<UserResponse> getAllUsersAccount() {
        return userRepository.findAllByRole_RoleName(RoleNameEnum.USER).stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }

    public List<OrderResponse> getUserPurchaseHistory(String orderStatus) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        if (orderStatus == null) {
            throw new OrderException("Trạng thái đơn hàng không hợp lệ!");
        } else if (orderStatus.equalsIgnoreCase("ALL")) {
            return orderRepository.findAllByUserAndExpiredAtAfterOrPaidAtIsNotNullOrderByCreatedAtDesc(user, Instant.now()).stream()
                    .map(orderServiceHelper::convertToOrderResponse)
                    .toList();
        } else if (orderStatus.equalsIgnoreCase("COMPLETED")) {
            return orderRepository.findAllByUserAndPaidAtIsNotNullOrderByCreatedAtDesc(user).stream()
                    .map(orderServiceHelper::convertToOrderResponse)
                    .toList();
        } else if (orderStatus.equalsIgnoreCase("PENDING")) {
            return orderRepository.findAllByUserAndPaidAtIsNullAndExpiredAtAfterOrderByCreatedAtDesc(user, Instant.now()).stream()
                    .map(orderServiceHelper::convertToOrderResponse)
                    .toList();
        } else if (orderStatus.equalsIgnoreCase("EXPIRED")) {
            return orderRepository.findAllByUserAndPaidAtIsNullAndExpiredAtLessThanEqualOrderByCreatedAtDesc(user, Instant.now()).stream()
                    .map(orderServiceHelper::convertToOrderResponse)
                    .toList();
        } else {
            throw new OrderException("Trạng thái đơn hàng không hợp lệ!");
        }
    }

    public List<ExpertDetailsResponse> getMyFollowingExperts() {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        return userEntity.getExperts().stream()
                .map(expertServiceHelper::convertToExpertDetailsResponse)
                .toList();
    }

    public List<CourseResponse> getAllCoursesRegister(String selectedTab) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new NotFoundException("Không tìm thấy người dùng!");
        }

        List<CourseResponse> listCourseRegister = courseServiceHelper.convertToCourseResponseList(userEntity.getCourses());

        if (selectedTab.equalsIgnoreCase("ALL")) {
            return listCourseRegister;
        } else if (selectedTab.equalsIgnoreCase("START")) {
            return listCourseRegister.stream().filter(course -> userServiceHelper.checkStatusCourse(course.getCourseId(), userEntity) * 100 == 0).toList();
        } else if (selectedTab.equalsIgnoreCase("COMPLETED")) {
            return listCourseRegister.stream().filter(course -> userServiceHelper.checkStatusCourse(course.getCourseId(), userEntity) * 100 == 100).toList();
        } else {
            return listCourseRegister.stream().filter(course -> {
                double percent = userServiceHelper.checkStatusCourse(course.getCourseId(), userEntity) * 100;
                return percent > 0 && percent < 100;
            }).toList();
        }
    }
}