package com.service;

import com.dto.request.ChapterRequest;
import com.entity.ChapterEntity;
import com.entity.CourseEntity;
import com.entity.UserEntity;
import com.entity.UserProgressEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.helper.UserServiceHelper;
import com.repository.ChapterRepository;
import com.repository.CourseRepository;
import com.repository.UserProgressRepository;
import com.util.enums.CourseStatusEnum;
import com.util.enums.RoleNameEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final CourseRepository courseRepository;
    private final UserServiceHelper userServiceHelper;
    private final ChapterRepository chapterRepository;
    private final ModelMapper modelMapper;
    private final UserProgressRepository userProgressRepository;

    public void createUpdateChapter(ChapterRequest chapterRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null || userEntity.getRole().getRoleName() != RoleNameEnum.EXPERT || userEntity.getExpert() == null) {
            throw new UserException("Vui lòng đăng nhập bằng tài khoản EXPERT để thực hiện chức năng này!");
        }
        CourseEntity courseEntity = courseRepository.findByCourseIdAndExpert(chapterRequest.getCourseId(), userEntity.getExpert())
                .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));

        if (chapterRequest.getChapterId() != null) {
            ChapterEntity currentChapter = chapterRepository.findById(chapterRequest.getChapterId())
                    .orElseThrow(() -> new NotFoundException("Chương học không tồn tại!"));
            currentChapter.setTitle(chapterRequest.getTitle());
            currentChapter.setDescription(chapterRequest.getDescription());
            chapterRepository.save(currentChapter);

            List<UserProgressEntity> userProgresses = userProgressRepository.findAllByChapterId(currentChapter.getChapterId());
            userProgressRepository.deleteAll(userProgresses);
        } else {
            ChapterEntity chapter = modelMapper.map(chapterRequest, ChapterEntity.class);
            chapter.setCourse(courseEntity);
            chapterRepository.save(chapter);
        }
        courseEntity.setCourseStatus(CourseStatusEnum.DRAFT);
        courseRepository.save(courseEntity);
    }

    public void deleteChapter(Long chapterId) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null || userEntity.getRole().getRoleName() != RoleNameEnum.EXPERT || userEntity.getExpert() == null) {
            throw new UserException("Vui lòng đăng nhập bằng tài khoản EXPERT để thực hiện chức năng này!");
        }
        
        ChapterEntity chapterEntity = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new NotFoundException("Chương học này không tồn tại!"));
        CourseEntity courseEntity = chapterEntity.getCourse();
        courseEntity.setCourseStatus(CourseStatusEnum.DRAFT);
        courseRepository.save(courseEntity);

        List<UserProgressEntity> userProgresses = userProgressRepository.findAllByChapterId(chapterId);
        userProgressRepository.deleteAll(userProgresses);
        chapterRepository.deleteById(chapterId);
    }
}
