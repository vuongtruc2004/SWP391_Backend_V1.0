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

    public List<Map<String, Long>> getCourseSalesCount() {
        List<Object[]> results = orderDetailsRepository.getCourseSalesCount();
        List<Map<String, Long>> courseSalesList = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Long> courseSalesMap = new HashMap<>();
            courseSalesMap.put((String) result[0], (Long) result[1]);
            courseSalesList.add(courseSalesMap);
        }

        return courseSalesList;
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
