package com.service;


import com.dto.request.CourseRequest;
import com.dto.response.ApiResponse;
import com.dto.response.CourseResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.CourseEntity;
import com.entity.ExpertEntity;
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
import com.repository.SubjectRepository;
import com.util.BuildResponse;
import com.util.CourseValidUtil;
import com.util.enums.CourseStatusEnum;
import com.util.enums.RoleNameEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private final SubjectRepository subjectRepository;

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
                criteriaBuilder.equal(root.get("courseStatus"), CourseStatusEnum.APPROVED)));

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

        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user != null && user.getCourses() != null && !user.getCourses().isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.not(root.get("courseId").in(
                            user.getCourses().stream()
                                    .map(CourseEntity::getCourseId)
                                    .toList()
                    )));
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

    public CourseDetailsResponse getCourseByIdAndApproved(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException("Khóa học không tồn tại!"));
        if (!courseEntity.getCourseStatus().equals(CourseStatusEnum.APPROVED)) {
            throw new CourseException("Khóa học không tồn tại hoặc đang được cập nhật lại!");
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
        if (!courseEntity.getCourseStatus().equals(CourseStatusEnum.APPROVED)) {
            throw new NotFoundException("Khóa học không tồn tại hoặc đang được cập nhật lại!");
        }
        return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
    }

    public List<CourseResponse> getSuggestedCourses(List<Long> courseIds) {
        List<Long> notCourseIds = new ArrayList<>(courseIds);
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity != null) {
            userEntity.getCourses().forEach(courseEntity -> notCourseIds.add(courseEntity.getCourseId()));
        }

        Set<Long> resultIds = courseRepository.findSuggestedCourseIds(courseIds, notCourseIds);
        return resultIds.stream()
                .map(id -> courseRepository.findByCourseIdAndCourseStatus(id, CourseStatusEnum.APPROVED).orElse(null))
                .filter(Objects::nonNull)
                .map(courseServiceHelper::convertToCourseResponse)
                .toList();
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
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Bạn cần đăng nhập để thực hiện chức năng này!");
        }

        if (userEntity.getRole().getRoleName().equals(RoleNameEnum.EXPERT)) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("expert").get("expertId"), userEntity.getExpert().getExpertId())
            );
        }

        if (!userEntity.getRole().getRoleName().equals(RoleNameEnum.EXPERT)) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    root.get("courseStatus").in(CourseStatusEnum.PROCESSING, CourseStatusEnum.APPROVED)
            );
        }

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
        if (courseEntity != null && (courseEntity.getUsers().isEmpty() || !courseEntity.getCourseStatus().equals(CourseStatusEnum.APPROVED))) {
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

    public CourseResponse createCourse(CourseRequest courseRequest) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null || user.getExpert() == null) {
            throw new UserException("Bạn phải đăng nhập bằng tài khoản EXPERT để thực hiện chức năng này!");
        }
        CourseValidUtil.validCourseTitleAndDescription(courseRequest.getCourseName(), courseRequest.getDescription());
        String[] courseName = courseRequest.getCourseName().trim().split("\\s+");
        String courseNameReplace = String.join(" ", courseName);
        CourseEntity currentCourse = courseRepository.findByCourseNameAndExpert(courseNameReplace, user.getExpert());
        if (currentCourse != null) {
            throw new NotFoundException("Khoá học đã tồn tại!");
        }

        CourseEntity courseEntity = modelMapper.map(courseRequest, CourseEntity.class);
        courseEntity.setObjectiveList(courseRequest.getObjectives());
        courseEntity.setExpert(user.getExpert());
        courseEntity.setCourseStatus(CourseStatusEnum.DRAFT);
        courseEntity.setSubjects(subjectRepository.findAllBySubjectNameIn(courseRequest.getSubjects().stream().map(String::trim).toList()));
        return courseServiceHelper.convertToCourseResponse(courseRepository.save(courseEntity));
    }

    @Transactional
    public CourseResponse updateCourse(CourseRequest courseRequest) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null || user.getExpert() == null) {
            throw new UserException("Bạn phải đăng nhập bằng tài khoản EXPERT để thực hiện chức năng này!");
        }
        CourseValidUtil.validCourseTitleAndDescription(courseRequest.getCourseName(), courseRequest.getDescription());
        CourseEntity currentCourse = courseRepository.findByCourseIdAndExpert(courseRequest.getCourseId(), user.getExpert())
                .orElseThrow(() -> new CourseException("Khóa học không tồn tại hoặc không thuộc sở hữu của bạn!"));

        currentCourse.setCourseName(courseRequest.getCourseName());
        currentCourse.setDescription(courseRequest.getDescription());
        currentCourse.setObjectiveList(courseRequest.getObjectives());
        currentCourse.setThumbnail(courseRequest.getThumbnail());
        currentCourse.setIntroduction(courseRequest.getIntroduction());
        currentCourse.setCourseStatus(CourseStatusEnum.DRAFT);
        currentCourse.setPrice(courseRequest.getPrice());
        currentCourse.setSubjects(subjectRepository.findAllBySubjectNameIn(courseRequest.getSubjects().stream().map(String::trim).toList()));
        return courseServiceHelper.convertToCourseResponse(courseRepository.save(currentCourse));
    }

    public void sendCourseToAdminForApprove(Long courseId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null || user.getExpert() == null) {
            throw new UserException("Bạn phải đăng nhập bằng tài khoản EXPERT để thực hiện chức năng này!");
        }
        CourseEntity course = courseRepository.findByCourseIdAndExpertAndCourseStatus(courseId, user.getExpert(), CourseStatusEnum.DRAFT);
        course.setCourseStatus(CourseStatusEnum.PROCESSING);
        courseRepository.save(course);
    }

    public CourseDetailsResponse getCourseDetailsAdmin(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học!"));
        return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
    }

    public List<CourseResponse> getLatestCoursesOfFollowingExperts() {
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        return courseServiceHelper.convertToCourseResponseList(courseRepository.findTop12ByExpertInAndCourseStatusOrderByCreatedAtDesc(user.getExperts(), CourseStatusEnum.APPROVED));
    }

    public List<CourseResponse> getAllCourse() {
        List<CourseEntity> courseEntities = this.courseRepository.findAll();
        return this.courseServiceHelper.convertToCourseResponseList(courseEntities);
    }

    public List<CourseResponse> getAllCoursesNotInCampaign() {
        Set<CourseEntity> courseEntities = courseRepository.findAllByCampaignIsNull();
        return this.courseServiceHelper.convertToCourseResponseList(courseEntities);
    }

    public void approvedCourse(Long courseId) {
        CourseEntity courseEntity = this.courseRepository.findById(courseId).orElse(null);
        courseEntity.setCourseStatus(CourseStatusEnum.APPROVED);
        courseRepository.save(courseEntity);
    }

    public void rejectedCourse(Long courseId) {
        CourseEntity courseEntity = this.courseRepository.findById(courseId).orElse(null);
        courseEntity.setCourseStatus(CourseStatusEnum.REJECTED);
        courseRepository.save(courseEntity);
    }
}
