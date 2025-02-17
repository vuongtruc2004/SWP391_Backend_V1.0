package com.service;


import com.dto.response.*;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.CourseEntity;
import com.entity.ExpertEntity;
import com.entity.LessonEntity;
import com.entity.UserEntity;
import com.exception.custom.CourseException;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.NotFoundException;
import com.helper.CourseServiceHelper;
import com.repository.*;
import com.service.auth.JwtService;
import com.util.BuildResponse;
import com.util.enums.AccountTypeEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseServiceHelper courseServiceHelper;
    private final LessonRepository lessonRepository;
    private final ExpertRepository expertRepository;
    private final SubjectRepository subjectRepository;
    private final UserProgressRepository userProgressRepository;

    public PageDetailsResponse<List<CourseResponse>> getCoursesWithFilter(
            Specification<CourseEntity> specification,
            Pageable pageable,
            String specialSort,
            String expertIds,
            String subjectIds
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
        specification = specification.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("accepted"), true)));

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

    public CourseDetailsResponse getCourseById(Long id) {
        CourseEntity courseEntity = courseRepository.findById(id)
                .orElseThrow(() -> new CourseException("Khóa học không tồn tại!"));
        if (Boolean.FALSE.equals(courseEntity.getAccepted())) {
            throw new CourseException("Khóa học không tồn tại!");
        }
        return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
    }

    public List<CourseDetailsResponse> getSuggestedCourses(List<Long> courseIds) {
        Set<Long> resultIds = courseRepository.findSuggestedCourseIds(courseIds);
        return resultIds.stream().map(id -> {
            CourseEntity courseEntity = courseRepository.findById(id).orElse(null);
            if (courseEntity == null) {
                return null;
            }
            return courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
        }).toList();
    }

    public PageDetailsResponse<List<CourseResponse>> getCoursesAndSortByPurchased(Pageable pageable) {
        Page<CourseEntity> page = courseRepository.findCoursesAndOrderByPurchasersDesc(pageable);
        List<CourseResponse> courseResponses = courseServiceHelper.convertToCourseResponseList(page.getContent());
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    public List<CourseStatusResponse> getPurchasedCourses() {
        String email = JwtService.extractUsernameFromToken().orElse(null);
        String accountType = JwtService.extractAccountTypeFromToken().orElse(null);
        if (email != null && accountType != null) {
            UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                    .orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại!"));
            List<CourseStatusResponse> courseStatusResponseList = new ArrayList<>();

            for (CourseEntity courseEntity : userEntity.getCourses()) {
                long numberOfVideos = courseServiceHelper.getNumbersOfVideos(courseEntity);
                long numberOfDocuments = courseServiceHelper.getNumbersOfDocuments(courseEntity);
                long numberOfCompletedVideosAndDocuments = userProgressRepository.countNumberOfCompletedVideosAndDocuments(userEntity.getUserId(), courseEntity.getCourseId());
                double completionPercentage = (double) numberOfCompletedVideosAndDocuments / (numberOfDocuments + numberOfVideos) * 100.0;

                CourseStatusResponse courseStatusResponse = new CourseStatusResponse();
                courseStatusResponse.setCourseId(courseEntity.getCourseId());
                courseStatusResponse.setCompletionPercentage(completionPercentage);
                courseStatusResponseList.add(courseStatusResponse);
            }
            return courseStatusResponseList;
        }
        return new ArrayList<>();
    }

    public PageDetailsResponse<List<CourseResponse>> getCoursesWithFilterByAdmin(
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
        List<CourseResponse> listCourseResponses = courseServiceHelper.convertToCourseResponseList(page.getContent());
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
                expert.getCourses().remove(courseEntity); // gỡ bỏ quan hệ (remove là xóa trong list)
                expertRepository.save(expert); // Save changes
                if (courseEntity.getLessons() != null) {
                    for (LessonEntity lessonEntity : courseEntity.getLessons()) {
                        lessonRepository.delete(lessonEntity);
                    }
                }
                courseRepository.deleteById(courseId);
            }
        } else {
            throw new InvalidRequestInput("Không thể xóa khóa học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Bạn đã xóa môn học có Id là " + courseId + " thành công!",
                null
        );
    }

    public ApiResponse<String> changeAcceptACourse(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).orElse(null);
        if (courseEntity != null) {
            courseEntity.setAccepted(true);
            courseRepository.save(courseEntity);
        } else {
            throw new InvalidRequestInput("Không tìm thấy khóa học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Khóa học " + courseEntity.getCourseName() + " đã được chấp nhận",
                null
        );
    }

    public ApiResponse<String> changeUnacceptACourse(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).orElse(null);
        if (courseEntity != null) {
            courseEntity.setAccepted(false);
            courseRepository.save(courseEntity);
        } else {
            throw new InvalidRequestInput("Không tìm thấy khóa học!");
        }
        return BuildResponse.buildApiResponse(
                HttpStatus.OK.value(),
                "Thành công!",
                "Khóa học " + courseEntity.getCourseName() + " đã được ẩn!",
                null
        );
    }
}
