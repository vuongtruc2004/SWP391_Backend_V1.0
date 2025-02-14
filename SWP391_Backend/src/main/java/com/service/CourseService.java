package com.service;


import com.dto.response.ApiResponse;
import com.dto.response.CourseResponse;
import com.dto.response.MinMaxPriceResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.*;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.NotFoundException;
import com.helper.CourseServiceHelper;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.service.auth.JwtService.extractUsernameFromToken;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CourseServiceHelper courseServiceHelper;
    private final LessonRepository lessonRepository;
    private final ExpertRepository expertRepository;
    private final LessonService lessonService;
    private final SubjectRepository subjectRepository;


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

    public PageDetailsResponse<List<CourseResponse>> getCoursesWithFilterRoleAdmin(
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
        List<CourseResponse> courseResponses = page.getContent()
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
                courseResponses
        );


    }

    public MinMaxPriceResponse getMaxMinPrice() {
        Double minPrice = courseRepository.findMinPrice();
        Double maxPrice = courseRepository.findMaxPrice();
        return new MinMaxPriceResponse(minPrice, maxPrice);
    }

    @Transactional
    public ApiResponse<String> deleteById(Long courseId) {
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

    public ApiResponse<String> changeAccept(Long courseId) {
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

    public CourseResponse createCourse(CourseEntity courseEntity) throws Exception {
        Optional<String> email = extractUsernameFromToken();
        UserEntity userEntity = this.userRepository.findByEmail(email.get());
        CourseEntity currentCourse = this.courseRepository.findByCourseNameAndExpert(courseEntity.getCourseName(), userEntity.getExpert());
        if (currentCourse != null) {
            throw new NotFoundException("Khoá học đã tồn tại !");
        }
        CourseEntity newCourse = new CourseEntity();
        newCourse.setExpert(userEntity.getExpert());
        newCourse.setCourseName(courseEntity.getCourseName());
        newCourse.setDescription(courseEntity.getDescription());
        newCourse.setObjectives(courseEntity.getObjectives());
        newCourse.setIntroduction(courseEntity.getIntroduction());
        newCourse.setOriginalPrice(courseEntity.getOriginalPrice());
        newCourse.setSalePrice(courseEntity.getSalePrice());
        newCourse.setThumbnail(courseEntity.getThumbnail());
        newCourse=this.courseRepository.save(newCourse);
        Set<SubjectEntity> subjectEntitySet=new HashSet<>();
        for(SubjectEntity subjectEntity : courseEntity.getSubjects()) {
            Boolean checkExistsSubject=this.subjectRepository.existsBySubjectName(subjectEntity.getSubjectName());
            if(checkExistsSubject) {
                SubjectEntity currentSubject=this.subjectRepository.findBySubjectName(subjectEntity.getSubjectName());
                subjectEntitySet.add(currentSubject);
            }else{
                this.subjectRepository.save(subjectEntity);
            }
        }
        newCourse.setSubjects(subjectEntitySet);
        for (LessonEntity lessonEntity : courseEntity.getLessons()) {
            lessonEntity.setCourse(newCourse);
            this.lessonService.save(lessonEntity);
        }
        this.courseRepository.save(newCourse);
        CourseResponse courseResponse=new CourseResponse();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(newCourse, courseResponse);
        return courseResponse;
    }

    public CourseResponse updateCourse(CourseEntity courseEntity) throws Exception {
        Optional<String> email = extractUsernameFromToken();
        UserEntity userEntity = this.userRepository.findByEmail(email.get());
        CourseEntity newCourse = this.courseRepository.findById(courseEntity.getCourseId()).orElse(null);
        newCourse.setExpert(userEntity.getExpert());
        newCourse.setCourseName(courseEntity.getCourseName());
        newCourse.setDescription(courseEntity.getDescription());
        newCourse.setObjectives(courseEntity.getObjectives());
        newCourse.setIntroduction(courseEntity.getIntroduction());
        newCourse.setOriginalPrice(courseEntity.getOriginalPrice());
        newCourse.setSalePrice(courseEntity.getSalePrice());
        newCourse.setThumbnail(courseEntity.getThumbnail());
        newCourse=this.courseRepository.save(newCourse);
        Set<SubjectEntity> subjectEntitySet=new HashSet<>();
        for(SubjectEntity subjectEntity : courseEntity.getSubjects()) {
            Boolean checkExistsSubject=this.subjectRepository.existsBySubjectName(subjectEntity.getSubjectName());
            if(checkExistsSubject) {
                SubjectEntity currentSubject=this.subjectRepository.findBySubjectName(subjectEntity.getSubjectName());
                subjectEntitySet.add(currentSubject);
            }else{
                this.subjectRepository.save(subjectEntity);
            }
        }
        newCourse.setSubjects(subjectEntitySet);
        for (LessonEntity lessonEntity : courseEntity.getLessons()) {
            if(lessonEntity.getLessonId()!=null){
                LessonEntity currentLesson=this.lessonRepository.findById(lessonEntity.getLessonId()).orElse(null);
                currentLesson.setTitle(lessonEntity.getTitle());
                currentLesson.setDescription(lessonEntity.getDescription());
                currentLesson.setVideos(lessonEntity.getVideos());
                currentLesson.setDescription(lessonEntity.getDescription());
                this.lessonService.save(currentLesson);
            }else{
                lessonEntity.setCourse(newCourse);
                this.lessonService.save(lessonEntity);
            }

        }
        this.courseRepository.save(newCourse);
        CourseResponse courseResponse=new CourseResponse();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(newCourse, courseResponse);
        return courseResponse;
    }

}
