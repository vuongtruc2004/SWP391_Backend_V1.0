package com.helper;

import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.repository.LessonRepository;
import com.repository.QuizRepository;
import com.repository.UserProgressRepository;
import com.repository.UserRepository;
import com.service.auth.JwtService;
import com.util.enums.AccountTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class UserServiceHelper {

    private final UserRepository userRepository;
    private final UserProgressRepository userProgressRepository;
    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;

    public UserServiceHelper(UserRepository userRepository, UserProgressRepository userProgressRepository, QuizRepository quizRepository, LessonRepository lessonRepository) {
        this.userRepository = userRepository;
        this.userProgressRepository = userProgressRepository;
        this.quizRepository = quizRepository;
        this.lessonRepository = lessonRepository;
    }

    public UserEntity extractUserFromToken() {
        String email = JwtService.extractUsernameFromToken().orElse(null);
        String accountType = JwtService.extractAccountTypeFromToken().orElse(null);

        if (email != null && accountType != null) {
            UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
            if (Boolean.TRUE.equals(userEntity.getLocked())) {
                throw new UserException("Không tìm thấy");
            }
            return userEntity;
        }
        return null;
    }

    public double checkStatusCourse(Long courseId, UserEntity user) {
        long progress = userProgressRepository.countByCourseIdAndUser(courseId, user);
        long totalOfCourse = (quizRepository.countByCourseId(courseId) != null ? quizRepository.countByCourseId(courseId) : 0) + (lessonRepository.countLessonsByCourse(courseId) != null ? lessonRepository.countLessonsByCourse(courseId) : 0);
        if (totalOfCourse == 0) {
            return 0.0;
        }
        return (double) progress / totalOfCourse;
    }
}
