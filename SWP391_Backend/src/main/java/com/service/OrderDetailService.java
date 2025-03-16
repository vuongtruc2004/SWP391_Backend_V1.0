package com.service;

import com.dto.response.ApiResponse;
import com.dto.response.OrderDetailsResponse;
import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.helper.UserServiceHelper;
import com.repository.CourseRepository;
import com.repository.OrderDetailsRepository;
import com.repository.OrderRepository;
import com.util.BuildResponse;
import com.util.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final CourseRepository courseRepository;
    private final UserServiceHelper userServiceHelper;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

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
            Long countSold = this.orderDetailsRepository.countByCourse_CourseIdAndOrder_OrderStatus(courseId, OrderStatusEnum.COMPLETED);
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

    public ApiResponse<OrderDetailsResponse> getCoursePurchased(Long courseId) {
        UserEntity user = userServiceHelper.extractUserFromToken();
        List<OrderEntity> orderList = orderRepository.findByUser_UserId(user.getUserId());
        if (orderList.size() != 0) {
            for (OrderEntity orderEntity : orderList) {
                Optional<OrderDetailsEntity> orderDetailOptional = orderDetailsRepository.findByOrder_OrderIdAndCourse_CourseId(orderEntity.getOrderId(), courseId);
                if (orderDetailOptional.isPresent()) {
                    OrderDetailsEntity orderDetails = orderDetailOptional.get();
                    return BuildResponse.buildApiResponse(
                            HttpStatus.OK.value(),
                            "Thành công!",
                            null,
                            modelMapper.map(orderDetails, OrderDetailsResponse.class)
                    );
                }
            }
        } else {
            throw new NotFoundException("Không tìm thấy dữ liệu!");
        }
        return null;
    }
}
