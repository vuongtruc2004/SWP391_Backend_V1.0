package com.service;

import com.dto.response.*;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.repository.OrderRepository;
import com.util.BuildResponse;
import com.util.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public PageDetailsResponse<List<OrderResponse>> getOrdersWithFilters(
            Pageable pageable,
            Specification<OrderEntity> specification) {
        Page<OrderEntity> page = orderRepository.findAll(specification, pageable);

        List<OrderResponse> orderResponses = page.getContent()
                .stream()
                .map(orderEntity -> {
                    OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
                    UserEntity userEntity = orderEntity.getUser();
                    orderResponse.setUser(modelMapper.map(userEntity, UserResponse.class));
                    return orderResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                orderResponses);
    }

    public MinMaxPriceResponse getMaxMinPriceOfOrder() {
        Double minPrice = orderRepository.findMinTotalAmount();
        Double maxPrice = orderRepository.findMaxTotalAmount();

        return new MinMaxPriceResponse(minPrice, maxPrice);
    }

    public Map<String, Long> countOrdersOnEachDayOfWeek(LocalDate startOfWeek, LocalDate endOfWeek) {
        Map<String, Long> dayOfWeekCounts = new HashMap<>();
        LocalDate today = LocalDate.now();

        List<OrderEntity> completedOrders = orderRepository.findAll().stream()
                .filter(order -> order.getPaidAt() != null)
                .filter(order -> isInSelectedWeek(
                        order.getPaidAt().atZone(ZoneId.systemDefault()).toLocalDate(),
                        startOfWeek,
                        endOfWeek
                ))
                .toList();

        for (DayOfWeek day : DayOfWeek.values()) {
            String dayName = day.name();
            LocalDate currentDay = startOfWeek.with(day); // Lấy ngày thực tế của từng thứ trong tuần

            if (currentDay.isAfter(today)) {
                dayOfWeekCounts.put(dayName, 0L);
            } else {
                long count = completedOrders.stream()
                        .filter(order -> order.getPaidAt()
                                .atZone(ZoneId.systemDefault()) // Chuyển Instant → ZonedDateTime
                                .toLocalDate()
                                .getDayOfWeek() == day // So sánh ngày trong tuần
                        )
                        .mapToLong(order -> order.getOrderDetails().size()) // Lấy số lượng OrderDetails của mỗi Order
                        .sum();
                dayOfWeekCounts.put(dayName, count);
            }
        }
        return dayOfWeekCounts;
    }


    public DashboardStatisticsResponse getDashboardStatistics(String type) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        DashboardStatisticsResponse response = new DashboardStatisticsResponse();
        if (type.equals("week")) {
            response.setRevenue(orderRepository.getCurrentWeekRevenue());
            response.setStudents(orderRepository.getCurrentWeekStudents());
            response.setOrders(orderRepository.getCurrentWeekOrders());
        } else if (type.equals("month")) {
            response.setRevenue(orderRepository.getCurrentMonthRevenue());
            response.setStudents(orderRepository.getCurrentMonthStudents());
            response.setOrders(orderRepository.getCurrentMonthOrders());
        } else if (type.equals("quarter")) {
            response.setRevenue(orderRepository.getCurrentQuarterRevenue());
            response.setStudents(orderRepository.getCurrentQuarterStudents());
            response.setOrders(orderRepository.getCurrentQuarterOrders());
        } else {
            response.setRevenue(orderRepository.getCurrentYearRevenue());
            response.setStudents(orderRepository.getCurrentYearStudents());
            response.setOrders(orderRepository.getCurrentYearOrders());
        }
        response.setTodayRevenue(orderRepository.getTodayRevenue());
        response.setTodayOrders(orderRepository.getTodayOrders());
        response.setTodayStudents(orderRepository.getTodayStudents());
        response.setYesterdayRevenue(orderRepository.getYesterdayRevenue(yesterday));
        response.setYesterdayStudents(orderRepository.getYesterdayStudents(yesterday));
        response.setYesterdayOrders(orderRepository.getYesterdayOrders(yesterday));

        return response;
    }


    private boolean isInSelectedWeek(LocalDate calculatedAt, LocalDate startOfWeek, LocalDate endOfWeek) {
        return !calculatedAt.isBefore(startOfWeek) && !calculatedAt.isAfter(endOfWeek);
    }
}
