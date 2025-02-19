package com.repository;

import com.entity.CourseEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaSpecificationRepository<CourseEntity, Long> {

    @Query("SELECT c FROM CourseEntity c " +
            "WHERE c.accepted = true " +
            "AND c.courseId NOT IN :courseIds " +
            "ORDER BY size(c.users) DESC")
    Page<CourseEntity> findCoursesAndOrderByPurchasersDesc(Pageable pageable, @Param("courseIds") Set<Long> courseIds);

    @Query("SELECT c FROM CourseEntity c " +
            "WHERE c.accepted = true " +
            "ORDER BY size(c.users) DESC")
    Page<CourseEntity> findCoursesAndOrderByPurchasersDesc(Pageable pageable);

    @Query(value = """
                SELECT c.course_id
                FROM course_subject c
                INNER JOIN (
                    SELECT subject_id, COUNT(course_id) AS numOfCourses
                    FROM course_subject
                    WHERE course_id IN (:courseIds)
                    GROUP BY subject_id
                    ORDER BY numOfCourses DESC
                ) AS temp ON c.subject_id = temp.subject_id
                WHERE c.course_id NOT IN (:notCourseIds)
                GROUP BY c.course_id
                ORDER BY MAX(temp.numOfCourses) DESC
                LIMIT 10
            """, nativeQuery = true)
    Set<Long> findSuggestedCourseIds(@Param("courseIds") List<Long> courseIds, @Param("notCourseIds") List<Long> notCourseIds);

    @Query("SELECT MIN(c.originalPrice) FROM CourseEntity c")
    Double findMinPrice(); // Giá nhỏ nhất

    @Query("SELECT MAX(c.originalPrice) FROM CourseEntity c")
    Double findMaxPrice(); // Giá lớn nhất
}
