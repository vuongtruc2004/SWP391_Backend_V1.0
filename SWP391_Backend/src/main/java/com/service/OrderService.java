package com.service;

import com.dto.request.CourseOrderRequest;
import com.dto.request.CreateOrderRequest;
import com.dto.response.*;
import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.exception.custom.CourseException;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;
    private final OrderServiceHelper orderServiceHelper;

    public OrderResponse createOrder(CreateOrderRequest orderRequest) {
        List<OrderEntity> orderEntityList = orderRepository.findByUserIdAndCourseIds(
                orderRequest.getUserId(),
                orderRequest.getCourseOrders().stream().map(CourseOrderRequest::getCourseId).toList()
        );
        if (!orderEntityList.isEmpty()) {
            throw new InvalidRequestInput("Người dùng đã có phiếu với khóa học này rồi!");
        }
        UserEntity userEntity = userRepository.findByUserIdAndLockedFalse(orderRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Người dùng không tồn tại!"));
        Set<CourseEntity> purchasedCourses = userEntity.getCourses();

        OrderEntity orderEntity = new OrderEntity();
        Set<OrderDetailsEntity> orderDetailsEntities = new HashSet<>();
        orderEntity.setUser(userEntity);

        for (CourseOrderRequest courseOrderRequest : orderRequest.getCourseOrders()) {
            CourseEntity courseEntity = courseRepository.findByCourseIdAndAcceptedTrue(courseOrderRequest.getCourseId())
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));
            if (purchasedCourses.contains(courseEntity)) {
                throw new CourseException("Bạn đã mua khóa học này rồi!");
            }
            OrderDetailsEntity orderDetailsEntity = OrderDetailsEntity.builder()
                    .order(orderEntity)
                    .price(courseOrderRequest.getPrice())
                    .courseId(courseOrderRequest.getCourseId())
                    .build();
            orderDetailsEntities.add(orderDetailsEntity);
        }
        orderEntity.setOrderDetails(orderDetailsEntities);
        OrderEntity newOrder = orderRepository.save(orderEntity);
        return modelMapper.map(newOrder, OrderResponse.class);
    }

    public OrderResponse activeCoursesForUser(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Hóa đơn không tồn tại!"));

        if (orderEntity.getOrderStatus().equals(OrderStatusEnum.PENDING)) {
            for (OrderDetailsEntity orderDetailsEntity : orderEntity.getOrderDetails()) {
                CourseEntity courseEntity = courseRepository.findByCourseIdAndAcceptedTrue(orderDetailsEntity.getCourseId())
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
            Specification<OrderEntity> specification,
            String minPrice,
            String maxPrice) {

        Double minPriceDouble = orderServiceHelper.parseDoubleOrNull(minPrice);
        Double maxPriceDouble = orderServiceHelper.parseDoubleOrNull(maxPrice);


        if (minPriceDouble != null || maxPriceDouble != null) {
            specification = specification.and(orderServiceHelper.filterByPrice(minPriceDouble, maxPriceDouble));
        }
        Page<OrderEntity> page = orderRepository.findAll(specification, pageable);

        List<OrderResponse> orderResponses = page.getContent()
                .stream()
                .map(orderEntity -> {
                    OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);

                    Set<OrderDetailsResponse> orderDetailsResponses = orderEntity.getOrderDetails().stream()
                            .map(orderDetailsEntity -> {
                                OrderDetailsResponse orderDetailsResponse = modelMapper.map(orderDetailsEntity, OrderDetailsResponse.class);
                                CourseEntity courseEntity = courseRepository.findById(orderDetailsEntity.getCourseId())
                                        .orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học"));
                                orderDetailsResponse.setCourse(modelMapper.map(courseEntity, CourseResponse.class));
                                return orderDetailsResponse;
                            })
                            .collect(Collectors.toSet());

                    orderResponse.setOrderDetails(orderDetailsResponses);
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
        OrderEntity minOrder = orderRepository.findOrderWithMinTotalPrice().orElse(null);
        OrderEntity maxOrder = orderRepository.findOrderWithMaxTotalPrice().orElse(null);
        if (minOrder == null && maxOrder == null) {
            return new MinMaxPriceResponse(0.0, 0.0);
        }
        Double minPrice = minOrder.getOrderDetails().stream().mapToDouble(OrderDetailsEntity::getPrice).sum();
        Double maxPrice = maxOrder.getOrderDetails().stream().mapToDouble(OrderDetailsEntity::getPrice).sum();

        return new MinMaxPriceResponse(minPrice, maxPrice);
    }

    public Map<String, Long> countOrdersOnEachDayOfWeek(LocalDate startOfWeek, LocalDate endOfWeek) {
        Map<String, Long> dayOfWeekCounts = new HashMap<>();
        LocalDate today = LocalDate.now();

        // Lọc danh sách đơn hàng có trạng thái COMPLETED và trong khoảng ngày được chọn
        List<OrderEntity> completedOrders = orderRepository.findAll().stream()
                .filter(order -> order.getOrderStatus() == OrderStatusEnum.COMPLETED) // Chỉ lấy đơn hàng COMPLETED
                .filter(order -> isInSelectedWeek(
                        order.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDate(),
                        startOfWeek,
                        endOfWeek
                ))
                .toList();

        for (DayOfWeek day : DayOfWeek.values()) {
            String dayName = day.name();
            LocalDate currentDay = startOfWeek.with(day); // Lấy ngày thực tế của từng thứ trong tuần

            if (currentDay.isAfter(today)) {
                // Nếu ngày nằm sau hôm nay, đặt giá trị = 0
                dayOfWeekCounts.put(dayName, 0L);
            } else {
                // Đếm số bản ghi có trạng thái COMPLETED theo từng ngày
                long count = completedOrders.stream()
                        .filter(order -> order.getCreatedAt()
                                .atZone(ZoneId.systemDefault()) // Chuyển Instant → ZonedDateTime
                                .toLocalDate() // Lấy LocalDate
                                .getDayOfWeek() == day // So sánh ngày trong tuần
                        )
                        .count();
                dayOfWeekCounts.put(dayName, count);
            }
        }

        return dayOfWeekCounts;
    }

    public DashboardStatisticsResponse getDashboardStatistics() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        return new DashboardStatisticsResponse(
                orderRepository.getTotalRevenue(OrderStatusEnum.COMPLETED),
                orderRepository.getTodayRevenue(OrderStatusEnum.COMPLETED),
                orderRepository.getYesterdayRevenue(yesterday),
                orderRepository.getTotalStudents(OrderStatusEnum.COMPLETED),
                orderRepository.getTodayStudents(OrderStatusEnum.COMPLETED),
                orderRepository.getYesterdayStudents(yesterday),
                orderRepository.getTotalOrders(OrderStatusEnum.COMPLETED),
                orderRepository.getTodayOrders(OrderStatusEnum.COMPLETED),
                orderRepository.getYesterdayOrders(yesterday)
        );
    }

    public List<CourseResponse> getCoursesByIds(List<Long> courseIds) {
        List<CourseEntity> courseEntityList = courseRepository.findAllById(courseIds);
        return courseEntityList.stream().map(courseEntity -> modelMapper.map(courseEntity, CourseResponse.class)).toList();
    }

    private boolean isInSelectedWeek(LocalDate calculatedAt, LocalDate startOfWeek, LocalDate endOfWeek) {
        return !calculatedAt.isBefore(startOfWeek) && !calculatedAt.isAfter(endOfWeek);
    }
}
