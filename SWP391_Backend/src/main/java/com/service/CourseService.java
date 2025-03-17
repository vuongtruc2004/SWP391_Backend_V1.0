package com.service;


import com.dto.request.CourseRequest;
import com.dto.response.ApiResponse;
import com.dto.response.CourseResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.CourseEntity;
import com.entity.ExpertEntity;
import com.entity.SubjectEntity;
import com.entity.UserEntity;
import com.exception.custom.CourseException;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.helper.CourseServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.ChapterRepository;
import com.repository.CourseRepository;
import com.repository.ExpertRepository;
import com.util.BuildResponse;
import com.util.CourseValidUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseServiceHelper courseServiceHelper;
    private final ChapterRepository chapterRepository;
    private final ExpertRepository expertRepository;
    private final UserServiceHelper userServiceHelper;
    private final ModelMapper modelMapper;
    private final SubjectService subjectService;

    public PageDetailsResponse<List<CourseResponse>> getCoursesWithFilter(
            Specification<CourseEntity> specification,
            Pageable pageable,
            String specialSort,
            String expertIds,
            String subjectIds,
            String event
    ) {
        try {
            if (specialSort != null && !specialSort.isBlank()) {
                String[] parts = specialSort.split(",");
                String sortOption = parts[0];
                String direction = parts[1];
                specification = specification.and(courseServiceHelper.sortBySpecialFields(sortOption, direction));
            }
        } catch (Exception e) {
            throw new InvalidRequestInput("Chuỗi không hợp lệ!");
        }
        specification = specification.and(courseServiceHelper.filterByAttribute(expertIds, "expert", "expertId"));
        specification = specification.and(courseServiceHelper.filterByAttribute(subjectIds, "subjects", "subjectId"));
        specification = specification.and(((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("accepted"), true)));

        if (event != null) {
            if (event.equalsIgnoreCase("sale")) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.isNotNull(root.get("campaign"))
                );
            } else if (event.equalsIgnoreCase("noSale")) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.isNull(root.get("campaign"))
                );
            }
        }

        Page<CourseEntity> page = courseRepository.findAll(specification, pageable);
        List<CourseResponse> courseResponses = courseServiceHelper.convertToCourseResponseList(page.getContent());
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    public CourseDetailsResponse getCourseByIdAndAccepted(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException("Khóa học không tồn tại!"));
        if (Boolean.FALSE.equals(courseEntity.getAccepted())) {
            throw new CourseException("Khóa học không tồn tại!");
        }
        return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
    }

    public CourseDetailsResponse getCourseById(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException("Khóa học không tồn tại!"));
        return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
    }

    public CourseDetailsResponse getCoursePurchasedByCourseId(Long courseId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        CourseEntity courseEntity = courseRepository.findPurchasedCourseByCourseId(courseId, userEntity.getUserId())
                .orElseThrow(() -> new NotFoundException("Bạn chưa mua khóa học này!"));
        return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
    }

    public List<CourseDetailsResponse> getSuggestedCourses(List<Long> courseIds) {
        List<Long> notCourseIds = new ArrayList<>(courseIds);
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity != null) {
            userEntity.getCourses().forEach(courseEntity -> notCourseIds.add(courseEntity.getCourseId()));
        }

        Set<Long> resultIds = courseRepository.findSuggestedCourseIds(courseIds, notCourseIds);
        return resultIds.stream().map(id -> {
            CourseEntity courseEntity = courseRepository.findById(id).orElse(null);
            if (courseEntity == null) {
                return null;
            }
            return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
        }).toList();
    }

    public PageDetailsResponse<List<CourseResponse>> getCoursesAndSortByPurchased(Pageable pageable) {
        Set<Long> courseIds = new HashSet<>();
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity != null) {
            userEntity.getCourses().forEach(courseEntity -> courseIds.add(courseEntity.getCourseId()));
        }
        Page<CourseEntity> page = courseIds.isEmpty() ?
                courseRepository.findCoursesAndOrderByPurchasersDesc(pageable) :
                courseRepository.findCoursesAndOrderByPurchasersDesc(pageable, courseIds);
        List<CourseResponse> courseResponses = courseServiceHelper.convertToCourseResponseList(page.getContent());
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    public List<Long> getPurchasedCourseIds() {
        List<Long> purchasedCourseIds = new ArrayList<>();
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity != null) {
            for (CourseEntity courseEntity : userEntity.getCourses()) {
                purchasedCourseIds.add(courseEntity.getCourseId());
            }
        }
        return purchasedCourseIds;
    }

    public PageDetailsResponse<List<CourseDetailsResponse>> getCoursesWithFilterByAdmin(
            Pageable pageable,
            Specification<CourseEntity> specification,
            Boolean accepted
    ) {
        if (accepted != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("accepted"), accepted)
            );
        }
        Page<CourseEntity> page = courseRepository.findAll(specification, pageable);
        List<CourseDetailsResponse> listCourseResponses = page.getContent().stream().map(courseServiceHelper::convertToCourseDetailsResponse).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                listCourseResponses
        );
    }

    public MinMaxPriceResponse getMaxMinPriceOfCourses() {
        Double minPrice = courseRepository.findMinPrice();
        Double maxPrice = courseRepository.findMaxPrice();
        return new MinMaxPriceResponse(minPrice, maxPrice);
    }

    @Transactional
    public ApiResponse<String> deleteByCourseId(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).orElse(null);
        ExpertEntity expert = expertRepository.findByCourses(courseEntity);
        if (courseEntity != null && (courseEntity.getUsers().isEmpty() || !courseEntity.getAccepted())) {
            if (expert != null) {
                expert.getCourses().remove(courseEntity);
                expertRepository.save(expert);
                if (courseEntity.getChapters() != null) {
                    chapterRepository.deleteAll(courseEntity.getChapters());
                }
                courseRepository.deleteById(courseId);
            }
        } else {
            throw new InvalidRequestInput("Không thể xóa khóa học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã xóa khóa học có Id là " + courseId + " thành công!",
                null
        );
    }

    public ApiResponse<String> changeAcceptStatusOfCourse(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học!"));

        if (courseEntity.getChapters() == null || courseEntity.getChapters().isEmpty()) {
            throw new InvalidRequestInput("Khóa học này chưa có bài giảng, không thể kích hoạt");
        }

        String message = "ẩn";
        if (Boolean.TRUE.equals(courseEntity.getAccepted())) {
            courseEntity.setAccepted(false);
        } else {
            message = "chấp nhận";
            courseEntity.setAccepted(true);
        }

        courseRepository.save(courseEntity);
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Khóa học " + courseEntity.getCourseName() + " đã được " + message,
                null
        );
    }

    public CourseResponse createCourse(CourseRequest courseRequest) throws Exception {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        CourseValidUtil.validCourseTitleAndDescription(courseRequest.getCourseName(), courseRequest.getDescription());
        String[] courseName = courseRequest.getCourseName().trim().split("\\s+");
        String courseNameReplace = String.join(" ", courseName);
        CourseEntity currentCourse = this.courseRepository.findByCourseNameAndExpert(courseNameReplace, user.getExpert());
        if (currentCourse != null) {
            throw new NotFoundException("Khoá học đã tồn tại!");
        }

        CourseEntity newCourse = new CourseEntity();
        newCourse.setExpert(user.getExpert());
        newCourse.setCourseName(courseNameReplace);
        newCourse.setDescription(courseRequest.getDescription());
        newCourse.setObjectiveList(courseRequest.getObjectives());
        newCourse.setIntroduction(courseRequest.getIntroduction());
        newCourse.setPrice(courseRequest.getPrice());
        newCourse.setThumbnail(courseRequest.getThumbnail());
        newCourse = courseRepository.save(newCourse);
        Set<SubjectEntity> subjectEntitySet = subjectService.saveSubjectWithCourse(courseRequest);
        newCourse.setSubjects(subjectEntitySet);
        newCourse = courseRepository.save(newCourse);
        CourseResponse courseResponse = new CourseResponse();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(newCourse, courseResponse);
        return courseResponse;
    }

    @Transactional
    public CourseResponse updateCourse(CourseRequest courseRequest) throws Exception {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        CourseValidUtil.validCourseTitleAndDescription(courseRequest.getCourseName(), courseRequest.getDescription());
        CourseEntity newCourse = courseRepository.findById(courseRequest.getCourseId()).orElse(null);
        newCourse.setExpert(user.getExpert());
        newCourse.setCourseName(courseRequest.getCourseName());
        newCourse.setDescription(courseRequest.getDescription());
        newCourse.setObjectiveList(courseRequest.getObjectives());
        newCourse.setIntroduction(courseRequest.getIntroduction());
        newCourse.setPrice(courseRequest.getPrice());
        newCourse.setThumbnail(courseRequest.getThumbnail());
        newCourse = courseRepository.save(newCourse);
        Set<SubjectEntity> subjectEntitySet = subjectService.saveSubjectWithCourse(courseRequest);
        newCourse.setSubjects(subjectEntitySet);
        newCourse.setAccepted(false);
        courseRepository.save(newCourse);
        CourseResponse courseResponse = new CourseResponse();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(newCourse, courseResponse);
        return courseResponse;
    }

    public CourseDetailsResponse getCourseDetailsAdmin(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học!"));
        CourseDetailsResponse courseDetailsResponse = modelMapper.map(courseEntity, CourseDetailsResponse.class);
        return courseDetailsResponse;
    }

    public List<CourseResponse> getLatestCoursesOfFollowingExperts() {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        return courseServiceHelper.convertToCourseResponseList(courseRepository.findTop12ByExpertInAndAcceptedTrueOrderByCreatedAtDesc(user.getExperts()));
    }
    public List<CourseResponse> getAllCourse(){
        List<CourseEntity> courseEntities=this.courseRepository.findAll();
        List<CourseResponse> courseResponses=this.courseServiceHelper.convertToCourseResponseList(courseEntities);
        return courseResponses;
    }
}
