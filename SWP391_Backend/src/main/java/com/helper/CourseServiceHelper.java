package com.helper;

import com.dto.response.CourseDetailsResponse;
import com.dto.response.CourseResponse;
import com.entity.CourseEntity;
import com.exception.custom.InvalidRequestInput;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CourseServiceHelper {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceHelper.class);
    private final ModelMapper modelMapper;

    public CourseServiceHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CourseResponse> convertToListResponse(Page<CourseEntity> page) {
        return page.getContent()
                .stream().map(courseEntity -> {
                    CourseResponse courseResponse = modelMapper.map(courseEntity, CourseResponse.class);
                    courseResponse.setTotalPurchased(courseEntity.getUsers().size());
                    return courseResponse;
                })
                .toList();
    }

    public CourseDetailsResponse convertToCourseDetailsResponse(CourseEntity courseEntity) {
        CourseDetailsResponse courseDetailsResponse = modelMapper.map(courseEntity, CourseDetailsResponse.class);
        courseDetailsResponse.setObjectives(courseEntity.getObjectiveList());
        courseDetailsResponse.setTotalPurchased(courseEntity.getUsers().size());
        courseDetailsResponse.setTotalLikes(courseEntity.getLikes().size());
        courseDetailsResponse.setTotalComments(courseEntity.getComments().size());
        return courseDetailsResponse;
    }

    public Specification<CourseEntity> sortBySpecialFields(
            String sortOption,
            String direction
    ) {
        return ((root, query, criteriaBuilder) -> {
            Expression<?> sortField;
            switch (sortOption) {
                case "purchaser": {
                    sortField = criteriaBuilder.size(root.get("users"));
                    break;
                }
                case "like": {
                    sortField = criteriaBuilder.size(root.get("likes"));
                    break;
                }
                case "comment": {
                    sortField = criteriaBuilder.size(root.get("comments"));
                    break;
                }
                default: {
                    throw new InvalidRequestInput("Chuỗi không hợp lệ!");
                }
            }

            if (query == null) {
                return criteriaBuilder.conjunction();
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
