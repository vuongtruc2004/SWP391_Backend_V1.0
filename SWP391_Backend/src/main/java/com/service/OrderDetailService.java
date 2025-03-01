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
    public List<Map.Entry<String, Long>> count() {
        // Lấy danh sách tất cả courseId đã được bán
        List<Long> soldCourseIds = this.orderDetailsRepository.findAllCourseId();

        // Lấy toàn bộ khóa học
        Map<Long, String> courseMap = this.courseRepository.findAll()
                .stream()
                .collect(Collectors.toMap(CourseEntity::getCourseId, CourseEntity::getCourseName));

        // Dùng LinkedHashMap để giữ thứ tự nhập vào
        Map<String, Long> courseSold = new LinkedHashMap<>();

        // Lặp qua danh sách course đã bán để tính tổng số lượng
        for (Long courseId : soldCourseIds) {
            String courseName = courseMap.get(courseId);
            Long countSold = this.orderDetailsRepository.countByCourseIdAndOrder_OrderStatus(courseId, OrderStatusEnum.COMPLETED);
            courseSold.put(courseName, countSold);
        }

        // Xử lý các khóa học chưa bán
        for (Map.Entry<Long, String> entry : courseMap.entrySet()) {
            if (!courseSold.containsKey(entry.getValue())) {
                courseSold.put(entry.getValue(), 0L);
            }
        }

        // Sắp xếp danh sách theo số lượng bán giảm dần
        return courseSold.entrySet()
                .stream()
                .sorted(Comparator.comparingLong(Map.Entry<String, Long>::getValue).reversed())
                .collect(Collectors.toList());
    }
}
