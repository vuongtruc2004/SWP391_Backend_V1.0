package com.service;

import com.dto.response.*;
import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.exception.custom.InvalidRequestInput;
import com.exception.custom.NotFoundException;
import com.helper.OrderServiceHelper;
import com.repository.CourseRepository;
import com.repository.OrderRepository;
import com.repository.UserRepository;
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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;
    private final OrderServiceHelper orderServiceHelper;

    public OrderResponse activeCoursesForUser(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Hóa đơn không tồn tại!"));

        if (orderEntity.getOrderStatus().equals(OrderStatusEnum.PENDING)) {
            for (OrderDetailsEntity orderDetailsEntity : orderEntity.getOrderDetails()) {
                CourseEntity courseEntity = courseRepository.findByCourseIdAndAcceptedTrue(orderDetailsEntity.getCourse().getCourseId())
                        .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));
                Set<UserEntity> currentRegister = courseEntity.getUsers();
                currentRegister.add(orderEntity.getUser());
                courseEntity.setUsers(currentRegister);
                courseRepository.save(courseEntity);
            }
            orderEntity.setOrderStatus(OrderStatusEnum.COMPLETED);
            OrderEntity updatedOrder = orderRepository.save(orderEntity);
            notificationService.purchaseSuccessNotification();
            return modelMapper.map(updatedOrder, OrderResponse.class);
        } else {
            throw new InvalidRequestInput("Bạn đã thanh toán hóa đơn này rồi!");
        }
    }

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

        // Lọc danh sách Order có trạng thái COMPLETED và nằm trong tuần
        List<OrderEntity> completedOrders = orderRepository.findAll().stream()
                .filter(order -> order.getOrderStatus() == OrderStatusEnum.COMPLETED) // Chỉ lấy order có trạng thái COMPLETED
                .filter(order -> isInSelectedWeek(
                        order.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDate(), // Lấy ngày tạo của Order
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
                // Đếm tổng số OrderDetails thuộc các Order có trạng thái COMPLETED theo từng ngày
                long count = completedOrders.stream()
                        .filter(order -> order.getCreatedAt()
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
            response.setRevenue(orderRepository.getCurrentWeekRevenue(OrderStatusEnum.COMPLETED));
            response.setStudents(orderRepository.getCurrentWeekStudents(OrderStatusEnum.COMPLETED));
            response.setOrders(orderRepository.getCurrentWeekOrders(OrderStatusEnum.COMPLETED));
        } else if (type.equals("month")) {
            response.setRevenue(orderRepository.getCurrentMonthRevenue(OrderStatusEnum.COMPLETED));
            response.setStudents(orderRepository.getCurrentMonthStudents(OrderStatusEnum.COMPLETED));
            response.setOrders(orderRepository.getCurrentMonthOrders(OrderStatusEnum.COMPLETED));
        } else if (type.equals("quarter")) {
            response.setRevenue(orderRepository.getCurrentQuarterRevenue(OrderStatusEnum.COMPLETED));
            response.setStudents(orderRepository.getCurrentQuarterStudents(OrderStatusEnum.COMPLETED));
            response.setOrders(orderRepository.getCurrentQuarterOrders(OrderStatusEnum.COMPLETED));
        } else {
            response.setRevenue(orderRepository.getCurrentYearRevenue(OrderStatusEnum.COMPLETED));
            response.setStudents(orderRepository.getCurrentYearStudents(OrderStatusEnum.COMPLETED));
            response.setOrders(orderRepository.getCurrentYearOrders(OrderStatusEnum.COMPLETED));
        }
        response.setTodayRevenue(orderRepository.getTodayRevenue(OrderStatusEnum.COMPLETED));
        response.setTodayOrders(orderRepository.getTodayOrders(OrderStatusEnum.COMPLETED));
        response.setTodayStudents(orderRepository.getTodayStudents(OrderStatusEnum.COMPLETED));
        response.setYesterdayRevenue(orderRepository.getYesterdayRevenue(yesterday, OrderStatusEnum.COMPLETED));
        response.setYesterdayStudents(orderRepository.getYesterdayStudents(yesterday, OrderStatusEnum.COMPLETED));
        response.setYesterdayOrders(orderRepository.getYesterdayOrders(yesterday, OrderStatusEnum.COMPLETED));

        return response;
    }

    private boolean isInSelectedWeek(LocalDate calculatedAt, LocalDate startOfWeek, LocalDate endOfWeek) {
        return !calculatedAt.isBefore(startOfWeek) && !calculatedAt.isAfter(endOfWeek);
    }
}
