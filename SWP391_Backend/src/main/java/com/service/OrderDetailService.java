package com.service;

import com.entity.CourseEntity;
import com.repository.CourseRepository;
import com.repository.OrderDetailsRepository;
import com.util.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
private final OrderDetailsRepository orderDetailsRepository;
private final CourseRepository courseRepository;
    public List<Map.Entry<String,Long>> count(){
        List<Long> courseIds = this.orderDetailsRepository.findAllCourseId();
        Map<String, Long> courseSold = new LinkedHashMap<>();
        for (Long courseId : courseIds) {
            String courseName = this.courseRepository.findById(courseId).get().getCourseName();
            Long countSold = this.orderDetailsRepository.countByCourseIdAndOrder_OrderStatus(courseId, OrderStatusEnum.COMPLETED);
            courseSold.put(courseName, countSold);
        }
        List<CourseEntity> courseIdRemains = this.courseRepository.findAll();
        for (CourseEntity course : courseIdRemains) {
            if (!courseIds.contains(course.getCourseId())) { // Sửa lỗi contains
                String courseName = course.getCourseName();
                Long countSold = 0L;
                courseSold.put(courseName, countSold);
            }

        }

        List<Map.Entry<String, Long> > list
                = new ArrayList<>(
                courseSold.entrySet());
        Collections.sort(
                list,
                new Comparator<Map.Entry<String, Long>>() {
                    public int compare(
                            Map.Entry<String, Long> entry1,
                            Map.Entry<String, Long> entry2)
                    {
                        return (int)(entry2.getValue() - entry1.getValue());

                    }
                });

        return list;
    }
}
