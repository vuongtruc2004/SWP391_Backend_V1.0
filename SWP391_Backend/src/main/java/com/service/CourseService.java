package com.service;


import com.dto.request.CourseRequest;
import com.dto.response.*;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.*;
import com.exception.custom.CourseException;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.NotFoundException;
import com.helper.CourseServiceHelper;
import com.helper.OrderServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.*;
import com.util.BuildResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.service.auth.JwtService.extractUsernameFromToken;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseServiceHelper courseServiceHelper;
    private final LessonRepository lessonRepository;
    private final ExpertRepository expertRepository;
    private final UserProgressRepository userProgressRepository;
    private final UserServiceHelper userServiceHelper;
    private final ModelMapper modelMapper;
    private final SubjectService subjectService;

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

    public List<CourseStatusResponse> getPurchasedCourses() {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity != null) {
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
        List<CourseDetailsResponse> listCourseResponses = page.getContent().stream().map(courseEntity -> {
            CourseDetailsResponse courseDetailsResponse = courseServiceHelper.convertToCourseDetailsResponse(courseEntity);
            return courseDetailsResponse;
        }).toList();
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
    public CourseResponse createCourse(CourseRequest courseRequest) throws Exception {
        Optional<String> email = extractUsernameFromToken();
        UserEntity userEntity = this.userRepository.findByEmail(email.get());
        CourseEntity currentCourse = this.courseRepository.findByCourseNameAndExpert(courseRequest.getCourseName(), userEntity.getExpert());

        if (currentCourse != null) {
            throw new NotFoundException("Khoá học đã tồn tại !");
        }

        CourseEntity newCourse = new CourseEntity();
        newCourse.setExpert(userEntity.getExpert());
        newCourse.setCourseName(courseRequest.getCourseName());
        newCourse.setDescription(courseRequest.getDescription());
        newCourse.setObjectiveList(courseRequest.getObjectives());
        newCourse.setIntroduction(courseRequest.getIntroduction());
        newCourse.setOriginalPrice(courseRequest.getOriginalPrice());
        newCourse.setSalePrice(courseRequest.getSalePrice());
        newCourse.setThumbnail(courseRequest.getThumbnail());
        newCourse=this.courseRepository.save(newCourse);
        Set<SubjectEntity> subjectEntitySet = this.subjectService.saveSubjectWithCourse(courseRequest);
        newCourse.setSubjects(subjectEntitySet);
        newCourse=this.courseRepository.save(newCourse);
        CourseResponse courseResponse=new CourseResponse();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(newCourse, courseResponse);
        return courseResponse;
    }

    @Transactional
    public CourseResponse updateCourse(CourseRequest courseRequest) throws Exception {
        Optional<String> email = extractUsernameFromToken();
        UserEntity userEntity = this.userRepository.findByEmail(email.get());
        CourseEntity newCourse = this.courseRepository.findById(courseRequest.getCourseId()).orElse(null);
        newCourse.setExpert(userEntity.getExpert());
        newCourse.setCourseName(courseRequest.getCourseName());
        newCourse.setDescription(courseRequest.getDescription());
        newCourse.setObjectiveList(courseRequest.getObjectives());
        newCourse.setIntroduction(courseRequest.getIntroduction());
        newCourse.setOriginalPrice(courseRequest.getOriginalPrice());
        newCourse.setSalePrice(courseRequest.getSalePrice());
        newCourse.setThumbnail(courseRequest.getThumbnail());
        newCourse=this.courseRepository.save(newCourse);
        Set<SubjectEntity> subjectEntitySet = this.subjectService.saveSubjectWithCourse(courseRequest);
        newCourse.setSubjects(subjectEntitySet);
        this.courseRepository.save(newCourse);
        CourseResponse courseResponse=new CourseResponse();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(newCourse, courseResponse);
        return courseResponse;
    }
}
