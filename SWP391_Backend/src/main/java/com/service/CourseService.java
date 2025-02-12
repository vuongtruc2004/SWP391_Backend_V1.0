package com.service;


import com.dto.response.ApiResponse;
import com.dto.response.CourseResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.CourseEntity;
import com.entity.ExpertEntity;
import com.entity.LessonEntity;
import com.entity.UserEntity;
import com.exception.custom.CourseException;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.NotFoundException;
import com.helper.CourseServiceHelper;
import com.repository.CourseRepository;
import com.repository.ExpertRepository;
import com.repository.LessonRepository;
import com.repository.UserRepository;
import com.util.BuildResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CourseServiceHelper courseServiceHelper;
    private final LessonRepository lessonRepository;
    private final ExpertRepository expertRepository;

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
        List<CourseResponse> courseResponses = courseServiceHelper.convertToListResponse(page);
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    public CourseResponse getCourseById(Long id) {
        CourseEntity courseEntity = courseRepository.findById(id)
                .orElseThrow(() -> new CourseException("Khóa học không tồn tại!"));
        return courseServiceHelper.convertToCourseResponse(courseEntity);
    }

    public PageDetailsResponse<List<CourseResponse>> getCoursesAndSortByPurchased(Pageable pageable) {
        Page<CourseEntity> page = courseRepository.findCoursesAndOrderByPurchasersDesc(pageable);
        List<CourseResponse> courseResponses = courseServiceHelper.convertToListResponse(page);
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
    }

    public PageDetailsResponse<List<CourseResponse>> getAllCoursesByUser(Long id, Pageable pageable) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mã người dùng không tồn tại!"));
        Page<CourseEntity> page = courseRepository.findAllByUsers(user, pageable);
        List<CourseResponse> courseResponses = courseServiceHelper.convertToListResponse(page);
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                courseResponses
        );
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
        List<CourseResponse> listCourseResponses = page.getContent()
                .stream().map(courseEntity -> {
                    CourseResponse courseResponse = modelMapper.map(courseEntity, CourseResponse.class);
                    courseResponse.setTotalPurchased(courseEntity.getUsers().size());
                    courseResponse.setTotalLikes(courseEntity.getLikes().size());
                    courseResponse.setTotalComments(courseEntity.getComments().size());
                    return courseResponse;
                })
                .toList();


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

    public ApiResponse<String> changeUnacceptACourse(Long courseId){
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
