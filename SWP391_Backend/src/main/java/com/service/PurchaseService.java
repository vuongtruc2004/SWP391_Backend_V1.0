package com.service;

import com.dto.request.PurchaseRequest;
import com.dto.response.OrderResponse;
import com.dto.response.PurchaseResponse;
import com.entity.CourseEntity;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.PurchaseException;
import com.exception.custom.UserException;
import com.helper.OrderServiceHelper;
import com.helper.PurchaseServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.CourseRepository;
import com.repository.OrderRepository;
import com.util.VnPayUtil;
import com.util.enums.CurrencyCodeEnum;
import com.util.enums.LocaleCodeEnum;
import com.util.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    public static final String VERSION = "2.1.0";
    public static final String COMMAND = "pay";
    public static final String ORDER_TYPE = "190000";
    public static final long DEFAULT_MULTIPLIER = 100L;
    private final PurchaseServiceHelper paymentServiceHelper;
    private final UserServiceHelper userServiceHelper;
    private final OrderRepository orderRepository;
    private final PurchaseServiceHelper purchaseServiceHelper;
    private final OrderServiceHelper orderServiceHelper;
    private final CourseRepository courseRepository;

    @Value("${vnpay.tmn.code}")
    private String tmnCode;

    @Value("${return.url}")
    private String returnUrl;

    @Value("${vnpay.timeout}")
    private Integer paymentTimeout;

    public PurchaseResponse createPurchaseRequest(PurchaseRequest purchaseRequest, String ipAddress) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }

        Calendar vnCalendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        String createDate = paymentServiceHelper.formatVnTime(vnCalendar);
        vnCalendar.add(Calendar.MINUTE, paymentTimeout);
        String expireDate = paymentServiceHelper.formatVnTime(vnCalendar);

        String txnRef = paymentServiceHelper.getTxnRef(purchaseRequest, userEntity.getUserId());

        OrderResponse orderResponse = createOrder(purchaseRequest, userEntity, txnRef, createDate, expireDate);

        long amount = purchaseRequest.getTotalPrice() * DEFAULT_MULTIPLIER;
        String orderInfo = paymentServiceHelper.getOrderInfo(purchaseRequest);

        Map<String, String> params = new HashMap<>();
        params.put(VnPayUtil.VERSION, VERSION);
        params.put(VnPayUtil.VNP_COMMAND, COMMAND);

        params.put(VnPayUtil.VNP_TMN_CODE, tmnCode);
        params.put(VnPayUtil.VNP_AMOUNT, String.valueOf(amount));
        params.put(VnPayUtil.VNP_CURRENCY_CODE, CurrencyCodeEnum.VND.name());

        params.put(VnPayUtil.VNP_TXN_REF, txnRef);
        params.put(VnPayUtil.VNP_RETURN_URL, returnUrl);

        params.put(VnPayUtil.VNP_CREATE_DATE, createDate);
        params.put(VnPayUtil.VNP_EXPIRE_DATE, expireDate);

        params.put(VnPayUtil.VNP_IP_ADDRESS, ipAddress);
        params.put(VnPayUtil.VNP_LOCALE, LocaleCodeEnum.VN.name().toLowerCase());

        params.put(VnPayUtil.VNP_ORDER_INFO, orderInfo);
        params.put(VnPayUtil.VNP_ORDER_TYPE, ORDER_TYPE);

        String paymentUrl = paymentServiceHelper.buildPaymentUrl(params);
        return PurchaseResponse.builder()
                .order(orderResponse)
                .redirectUrl(paymentUrl)
                .build();
    }

    public OrderResponse activeOrder(String orderCode) {
        List<OrderEntity> orderEntityList = orderRepository.findAllByOrderCode(orderCode);
        if (orderEntityList.size() != 1 || !orderEntityList.get(0).getOrderStatus().equals(OrderStatusEnum.PENDING)) {
            throw new PurchaseException("Đơn hàng không hợp lệ!");
        }

        orderEntityList.get(0).setOrderStatus(OrderStatusEnum.COMPLETED);

        for (OrderDetailsEntity orderDetailsEntity : orderEntityList.get(0).getOrderDetails()) {
            CourseEntity courseEntity = courseRepository.findByCourseIdAndAcceptedTrue(orderDetailsEntity.getCourse().getCourseId())
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));
            Set<UserEntity> currentRegister = courseEntity.getUsers();
            currentRegister.add(orderEntityList.get(0).getUser());
            courseEntity.setUsers(currentRegister);
            courseRepository.save(courseEntity);
        }

        OrderEntity newOrderEntity = orderRepository.save(orderEntityList.get(0));
        return orderServiceHelper.convertToOrderResponse(newOrderEntity);
    }

    public OrderResponse deleteOrder(Long orderId) {
        if (orderId == null) {
            throw new PurchaseException("OrderId không được null!");
        }
        OrderEntity orderEntity = orderRepository.findByOrderIdAndOrderStatusNot(orderId, OrderStatusEnum.COMPLETED)
                .orElseThrow(() -> new NotFoundException("Hóa đơn không tồn tại hoặc đã được thanh toán!"));
        orderRepository.delete(orderEntity);
        return orderServiceHelper.convertToOrderResponse(orderEntity);
    }

    public OrderResponse processIpn(Map<String, String> params) {
        if (!paymentServiceHelper.verifyIpn(params)) {
            throw new PurchaseException("Thông tin không hợp lệ!");
        }
        String txnRef = params.get(VnPayUtil.VNP_TXN_REF);
        String responseCode = params.get(VnPayUtil.VNP_RESPONSE_CODE);

        if (!responseCode.equals(VnPayUtil.VNP_SUCCESS_CODE)) {
            throw new PurchaseException("Thanh toán không thành công, vui lòng liên hệ với FanPage LearnGo để được hỗ trợ!");
        }
        return activeOrder(txnRef);
    }

    private OrderResponse createOrder(PurchaseRequest purchaseRequest, UserEntity userEntity, String txnRef, String createDate, String expireDate) {
        if (orderRepository.existsCompletedOrder(userEntity.getUserId(), purchaseRequest.getCourseIds())) {
            throw new PurchaseException("Bạn đã mua khóa học này rồi!");
        }

        OrderEntity orderEntity = new OrderEntity();
        List<OrderDetailsEntity> orderDetailsEntitySet = new ArrayList<>();
        orderEntity.setUser(userEntity);
        orderEntity.setOrderCode(txnRef);
        orderEntity.setTotalAmount(purchaseRequest.getTotalPrice());
        orderEntity.setCreatedAt(purchaseServiceHelper.parseVnTime(createDate));
        orderEntity.setExpiredAt(purchaseServiceHelper.parseVnTime(expireDate));

        for (Long courseId : purchaseRequest.getCourseIds()) {
            CourseEntity course = courseRepository.findByCourseIdAndAcceptedTrue(courseId)
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));
            orderDetailsEntitySet.add(OrderDetailsEntity.builder()
                    .order(orderEntity)
                    .course(course)
                    .build());
        }
        orderEntity.setOrderDetails(orderDetailsEntitySet);
        OrderEntity newOrderEntity = orderRepository.save(orderEntity);
        return orderServiceHelper.convertToOrderResponse(newOrderEntity);
    }
}
