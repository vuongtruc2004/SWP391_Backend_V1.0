package com.service;

import com.dto.request.OrderRequest;
import com.dto.response.ApiResponse;
import com.entity.*;
import com.exception.custom.*;
import com.helper.OrderServiceHelper;
import com.helper.PurchaseServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.CartCourseRepository;
import com.repository.CouponRepository;
import com.repository.CourseRepository;
import com.repository.OrderRepository;
import com.util.BuildResponse;
import com.util.VnPayUtil;
import com.util.enums.CourseStatusEnum;
import com.util.enums.CurrencyCodeEnum;
import com.util.enums.LocaleCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    private final CouponRepository couponRepository;
    private final CartCourseRepository cartCourseRepository;
    private final RedisService redisService;

    @Value("${vnpay.tmn.code}")
    private String tmnCode;

    @Value("${return.url}")
    private String returnUrl;

    @Value("${vnpay.timeout}")
    private Integer paymentTimeout;

    public ApiResponse<String> createPaymentURL(OrderRequest orderRequest, String ipAddress) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập!");
        }

        Calendar vnCalendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        String createDate = paymentServiceHelper.formatVnTime(vnCalendar);
        vnCalendar.add(Calendar.MINUTE, paymentTimeout);
        String expireDate = paymentServiceHelper.formatVnTime(vnCalendar);

        String txnRef = paymentServiceHelper.getTxnRef(orderRequest, userEntity.getUserId());

        OrderEntity order = createOrder(orderRequest, userEntity, txnRef, createDate, expireDate);

        long amount = (long) (order.getTotalPrice() * DEFAULT_MULTIPLIER);
        String orderInfo = "Thanh toan don hang " + txnRef;

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

        return BuildResponse.buildApiResponse(
                201,
                "Tạo phiếu mua hàng thành công!",
                null,
                paymentServiceHelper.buildPaymentUrl(order, params)
        );
    }

    public void activeOrder(String orderCode) {
        OrderEntity order = orderRepository.findByOrderCodeAndPaidAtIsNull(orderCode)
                .orElseThrow(() -> new OrderException("Bạn đã thanh toán hóa đơn này rồi hoặc hóa đơn không tồn tại!"));

        Set<CourseEntity> purchaseCourses = new HashSet<>();
        for (OrderDetailsEntity orderDetailsEntity : order.getOrderDetails()) {
            CourseEntity courseEntity = courseRepository.findByCourseIdAndCourseStatus(orderDetailsEntity.getCourse().getCourseId(), CourseStatusEnum.APPROVED)
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));

            purchaseCourses.add(courseEntity);

            Set<UserEntity> currentRegister = courseEntity.getUsers();
            currentRegister.add(order.getUser());
            courseEntity.setUsers(currentRegister);
            courseRepository.save(courseEntity);
        }

        order.setPaidAt(Instant.now());
        order.setPaymentUrl(null);
        orderRepository.save(order);

        CartEntity cart = order.getUser().getCart();
        if (cart != null && cart.getCartCourses() != null) {
            for (CourseEntity courseEntity : purchaseCourses) {
                CartCourseEntity cartCourseEntity = cartCourseRepository.findByCartAndCourse(cart, courseEntity).orElse(null);
                if (cartCourseEntity != null) {
                    cartCourseRepository.delete(cartCourseEntity);
                }
            }
        }
        redisService.removeOrderInRedis(order);
    }

    public void processIpn(Map<String, String> params) {
        if (!paymentServiceHelper.verifyIpn(params)) {
            throw new PurchaseException("Thông tin không hợp lệ!");
        }
        String txnRef = params.get(VnPayUtil.VNP_TXN_REF);

        OrderEntity order = orderRepository.findByOrderCode(txnRef)
                .orElseThrow(() -> new NotFoundException("Hóa đơn không tồn tại!"));

        String responseCode = params.get(VnPayUtil.VNP_RESPONSE_CODE);

        if (responseCode.equals(VnPayUtil.VNP_SUCCESS_CODE)) {
            activeOrder(txnRef);
            return;
        }

        if (responseCode.equals(VnPayUtil.VNP_OTHER_ERROR_CODE)) {
            throw new PurchaseException("Thanh toán không thành công, vui lòng liên hệ với FanPage LearnGo để được hỗ trợ!");
        } else if (responseCode.equals(VnPayUtil.VNP_CANCELLED_CODE)) {
            deleteOrder(order.getOrderId());
            throw new PurchaseException("Đã hủy thanh toán!");
        } else if (responseCode.equals(VnPayUtil.VNP_EXPIRED_CODE)) {
            throw new PurchaseException("Giao dịch đã hết hạn!");
        }
    }

    public void deleteOrder(Long orderId) {
        if (orderId == null) {
            throw new PurchaseException("OrderId không được null!");
        }
        OrderEntity orderEntity = orderRepository.findByOrderIdAndPaidAtIsNull(orderId)
                .orElseThrow(() -> new NotFoundException("Hóa đơn không tồn tại hoặc đã được thanh toán!"));
        redisService.removeOrderInRedis(orderEntity);
        orderServiceHelper.returnCouponThenDeleteOrder(orderEntity);
    }

    private OrderEntity createOrder(OrderRequest orderRequest, UserEntity userEntity, String txnRef, String createDate, String expireDate) {
        CouponEntity coupon = null;
        if (orderRequest.getCouponId() != null) {
            coupon = couponRepository.findById(orderRequest.getCouponId())
                    .orElseThrow(() -> new NotFoundException("Mã giảm giá không tồn tại!"));
            if (orderRepository.findByCouponAndPaidAtIsNull(coupon).isPresent()) {
                throw new PurchaseException("Bạn đang có hóa đơn chưa thanh toán | hết hạn sử dụng coupon này, vui lòng kiểm tra lại ở mục Hóa đơn của tôi!");
            }
        }

        OrderEntity orderEntity = new OrderEntity();
        List<OrderDetailsEntity> orderDetailsEntitySet = new ArrayList<>();
        orderEntity.setUser(userEntity);
        orderEntity.setOrderCode(txnRef);
        orderEntity.setCreatedAt(purchaseServiceHelper.parseVnTime(createDate));
        orderEntity.setExpiredAt(purchaseServiceHelper.parseVnTime(expireDate));

        List<CourseEntity> purchasedCourses = userEntity.getCourses();
        double totalPrice = 0.0;

        for (Long courseId : orderRequest.getCourseIds()) {
            CourseEntity course = courseRepository.findByCourseIdAndCourseStatus(courseId, CourseStatusEnum.APPROVED)
                    .orElseThrow(() -> new NotFoundException("Khóa học không tồn tại!"));

            if (purchasedCourses != null && purchasedCourses.contains(course)) {
                throw new CourseException("Bạn đã mua khóa học " + course.getCourseName() + " rồi!");
            }

            if (orderRepository.existsByCourseAndUser(course, userEntity)) {
                throw new PurchaseException("Bạn đã có 1 đơn hàng chứa khóa học này rồi, vui lòng kiểm tra lại ở mục Hóa đơn của tôi!");
            }

            CampaignEntity campaign = course.getCampaign();
            double priceOfCourseAtThisTime = course.getPrice();

            if (campaign != null && !campaign.getStartTime().isAfter(Instant.now()) && campaign.getEndTime().isAfter(Instant.now())) {
                priceOfCourseAtThisTime -= course.getPrice() * 1.0 * campaign.getDiscountPercentage() / 100;
            }
            totalPrice += priceOfCourseAtThisTime;

            orderDetailsEntitySet.add(OrderDetailsEntity.builder()
                    .order(orderEntity)
                    .course(course)
                    .priceAtTimeOfPurchase(priceOfCourseAtThisTime)
                    .build());
        }

        orderEntity.setOrderDetails(orderDetailsEntitySet);
        orderEntity.setTotalPrice(purchaseServiceHelper.applyCouponForOrder(coupon, totalPrice));

        if (coupon != null) {
            orderEntity.setCoupon(coupon);
        }
        OrderEntity newOrder = orderRepository.save(orderEntity);
        redisService.saveOrderToRedis(newOrder);
        return newOrder;
    }
}
