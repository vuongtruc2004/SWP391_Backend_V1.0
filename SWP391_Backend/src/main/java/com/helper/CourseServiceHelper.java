package com.helper;

import com.dto.response.ChapterResponse;
import com.dto.response.CourseResponse;
import com.dto.response.QuestionResponse;
import com.dto.response.details.CourseDetailsResponse;
import com.entity.CourseEntity;
import com.entity.RateEntity;
import com.exception.custom.InvalidRequestInput;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CourseServiceHelper {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceHelper.class);
    private final ModelMapper modelMapper;
    private final ExpertServiceHelper expertServiceHelper;

    public List<CourseResponse> convertToCourseResponseList(Collection<CourseEntity> collection) {
        return collection.stream().map(this::convertToCourseResponse).toList();
    }

    public CourseResponse convertToCourseResponse(CourseEntity courseEntity) {
        CourseResponse courseResponse = modelMapper.map(courseEntity, CourseResponse.class);
        courseResponse.setTotalPurchased(courseEntity.getUsers().size());
        courseResponse.setObjectives(courseEntity.getObjectiveList());
        courseResponse.setTotalLessons(
                courseEntity.getChapters().stream()
                        .mapToInt(chapter -> chapter.getLessons().size())
                        .sum()
        );
        courseResponse.setTotalQuizzes(
                courseEntity.getChapters().stream()
                        .mapToInt(chapter -> chapter.getQuizz() != null ? 1 : 0)
                        .sum()
        );
        return courseResponse;
    }

    public CourseDetailsResponse convertToCourseDetailsResponse(CourseEntity courseEntity) {
        List<ChapterResponse> chapterResponseList = courseEntity.getChapters().stream()
                .map(chapterEntity -> {
                    ChapterResponse chapterResponse = modelMapper.map(chapterEntity, ChapterResponse.class);
                    if (chapterEntity.getQuizz() != null && chapterEntity.getQuizz().getPublished()) {
                        chapterResponse.setQuizInfo(ChapterResponse.QuizInfoResponse.builder()
                                .quizId(chapterEntity.getQuizz().getQuizId())
                                .title(chapterEntity.getQuizz().getTitle())
                                .duration(chapterEntity.getQuizz().getDuration())
                                .allowSeeAnswers(chapterEntity.getQuizz().getAllowSeeAnswers())
                                .description(chapterEntity.getQuizz().getDescription())
                                .updatedAt(chapterEntity.getQuizz().getUpdatedAt())
                                .questions(chapterEntity.getQuizz().getQuestions().stream().map(questionEntity -> modelMapper.map(questionEntity, QuestionResponse.class)).toList())
                                .chapterId(chapterEntity.getChapterId())
                                .build());
                    }
                    return chapterResponse;
                }).toList();

        Set<RateEntity> rates = courseEntity.getRates();
        double averageRating = rates.stream().mapToInt(RateEntity::getStars).average().orElse(0.0);

        CourseDetailsResponse courseDetailsResponse = modelMapper.map(courseEntity, CourseDetailsResponse.class);
        courseDetailsResponse.setTotalPurchased(courseEntity.getUsers().size());
        courseDetailsResponse.setAverageRating(averageRating);
        courseDetailsResponse.setTotalLessons(
                courseEntity.getChapters().stream()
                        .mapToInt(chapter -> chapter.getLessons().size())
                        .sum()
        );
        courseDetailsResponse.setTotalQuizzes(
                courseEntity.getChapters().stream()
                        .mapToInt(chapter -> chapter.getQuizz() != null && chapter.getQuizz().getPublished() ? 1 : 0)
                        .sum()
        );
        courseDetailsResponse.setTotalRating(rates.size());
        courseDetailsResponse.setExpert(expertServiceHelper.convertToExpertDetailsResponse(courseEntity.getExpert()));
        courseDetailsResponse.setChapters(chapterResponseList);
        courseDetailsResponse.setObjectives(courseEntity.getObjectiveList());
        return courseDetailsResponse;
    }

    public Specification<CourseEntity> sortBySpecialFields(
            String sortOption,
            String direction
    ) {
        return ((root, query, criteriaBuilder) -> {
            if (query == null) {
                return criteriaBuilder.conjunction();
            }
            Expression<?> sortField;
            switch (sortOption) {
                case "purchaser": {
                    sortField = criteriaBuilder.size(root.get("users"));
                    break;
                }
                case "rate": {
                    Subquery<Double> subquery = query.subquery(Double.class);
                    Root<RateEntity> rateRoot = subquery.from(RateEntity.class);
                    subquery.select(criteriaBuilder.avg(rateRoot.get("stars")))
                            .where(criteriaBuilder.equal(rateRoot.get("course"), root));
                    sortField = subquery;
                    break;
                }
                default: {
                    throw new InvalidRequestInput("Chuỗi không hợp lệ!");
                }
            }

            if (direction.equalsIgnoreCase("asc")) {
                query.orderBy(criteriaBuilder.asc(sortField));
            } else if (direction.equalsIgnoreCase("desc")) {
                query.orderBy(criteriaBuilder.desc(sortField));
            } else {
                throw new InvalidRequestInput("Chuỗi không hợp lệ!");
            }

            return criteriaBuilder.conjunction();
        });
    }

    public Specification<CourseEntity> filterByAttribute(String ids, String attributeName, String joinIdField) {
        return (root, query, criteriaBuilder) -> {
            if (ids == null || ids.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            String[] parts = ids.split(",");
            Set<Long> idSet = new HashSet<>();
            for (String part : parts) {
                try {
                    idSet.add(Long.parseLong(part));
                } catch (NumberFormatException e) {
                    log.error("Error parsing ID: {}", part, e);
                }
            }
            if (idSet.isEmpty() || query == null) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            Join<CourseEntity, Object> join = root.join(attributeName, JoinType.INNER);
            return join.get(joinIdField).in(idSet);
        };
    }
}
