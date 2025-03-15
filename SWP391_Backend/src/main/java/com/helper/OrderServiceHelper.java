package com.helper;

import com.dto.response.*;
import com.entity.CourseEntity;
import com.entity.OrderEntity;
import com.exception.custom.NotFoundException;
import com.repository.CourseRepository;
import com.util.BuildResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderServiceHelper {
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    public OrderServiceHelper(ModelMapper modelMapper, CourseRepository courseRepository) {
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
    }


    public OrderResponse convertToOrderResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
        orderResponse.setUser(modelMapper.map(orderEntity.getUser(), UserResponse.class));
        return orderResponse;
    }


    public PageDetailsResponse<List<OrderResponse>> convertToPageOrderResponse(Page<OrderEntity> page) {
        List<OrderResponse> orderResponsesList = page.getContent().stream()
                .map(orderEntity -> {
                    OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
                    List<OrderDetailsResponse> orderDetailsResponses = orderEntity.getOrderDetails().stream()
                            .map(orderDetailsEntity -> {
                                OrderDetailsResponse orderDetailsResponse = modelMapper.map(orderDetailsEntity, OrderDetailsResponse.class);
                                CourseEntity course = courseRepository.findById(orderDetailsEntity.getCourseId())
                                        .orElseThrow(() -> new NotFoundException("Không tìm thấy khoa học"));
                                CourseResponse courseResponse = modelMapper.map(course, CourseResponse.class);
                                orderDetailsResponse.setCourse(courseResponse);
                                return orderDetailsResponse;
                            })
                            .collect(Collectors.toList());

                    orderResponse.setOrderDetails(orderDetailsResponses);
                    return orderResponse;
                }).toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                orderResponsesList
        );
    }
}
