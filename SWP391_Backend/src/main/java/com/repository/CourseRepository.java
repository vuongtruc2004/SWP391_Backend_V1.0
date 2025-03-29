package com.repository;

import com.entity.CourseEntity;
import com.entity.ExpertEntity;
import com.repository.custom.JpaSpecificationRepository;
import com.util.enums.CourseStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaSpecificationRepository<CourseEntity, Long> {

    @Query("select c from CourseEntity c " +
            "where c.courseStatus = 'APPROVED' and c.courseId not in (:courseIds) " +
            "order by size(c.users) desc")
    Page<CourseEntity> findCoursesAndOrderByPurchasersDesc(Pageable pageable, @Param("courseIds") Set<Long> courseIds);

    @Query("select c from CourseEntity c " +
            "where c.courseStatus = 'APPROVED'  " +
            "order by size(c.users) desc")
    Page<CourseEntity> findCoursesAndOrderByPurchasersDesc(Pageable pageable);

    Optional<CourseEntity> findByCourseIdAndCourseStatus(Long courseId, CourseStatusEnum courseStatus);

    @Query(value = """
                select c.course_id
                from course_subject c
                inner join (
                    select subject_id, COUNT(course_id) as numOfCourses
                    from course_subject
                    where course_id IN (:courseIds)
                    group by subject_id
                    order by numOfCourses desc
                ) as temp on c.subject_id = temp.subject_id
                where c.course_id not in (:notCourseIds)
                group by c.course_id
                order by MAX(temp.numOfCourses) desc
                limit 12
            """, nativeQuery = true)
    Set<Long> findSuggestedCourseIds(@Param("courseIds") List<Long> courseIds, @Param("notCourseIds") List<Long> notCourseIds);

    @Query("select MIN(c.price) FROM CourseEntity c")
    Double findMinPrice();

    @Query("select MAX(c.price) FROM CourseEntity c")
    Double findMaxPrice();

    CourseEntity findByCourseNameAndExpert(String courseName, ExpertEntity expert);

    Optional<CourseEntity> findByCourseIdAndExpert(Long courseId, ExpertEntity expert);

    @Query("select c from UserEntity u " +
            "join u.courses c " +
            "where c.courseId = :courseId and u.userId = :userId and c.courseStatus = 'APPROVED'")
    Optional<CourseEntity> findPurchasedCourseByCourseId(@Param("courseId") Long courseId, @Param("userId") Long userId);

    List<CourseEntity> findTop12ByExpertInAndCourseStatusOrderByCreatedAtDesc(Collection<ExpertEntity> experts, CourseStatusEnum courseStatus);

    Set<CourseEntity> findAllByCampaignIsNull();

    Set<CourseEntity> findAllByCampaignIsNullAndCourseIdIn(Set<Long> courseIds);

    CourseEntity findByCourseNameAndExpertAndCourseIdNot(String courseName, ExpertEntity expert, Long courseId);
}
