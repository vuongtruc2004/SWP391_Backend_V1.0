package com.configuration;
import com.entity.CouponEntity;
import com.repository.CouponRepository;
import com.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponConfig {
    private final CouponRepository couponRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void deleteCouponSuccess(){
        simpMessagingTemplate.convertAndSend("/topic/coupon","Delete Success!");
    }
    @Async
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void removeExpiredCoupons() {
        Instant now = Instant.now();
        List<CouponEntity> expiredCoupons = this.couponRepository.findByEndTimeBefore(now);
        List<CouponEntity> endCoupons=this.couponRepository.findAll();
        if (!expiredCoupons.isEmpty()) {
            couponRepository.deleteAll(expiredCoupons);
            deleteCouponSuccess();
        }
        for(CouponEntity coupon : endCoupons) {
            if(coupon.getMaxUses() == coupon.getUsedCount()){
                this.couponRepository.deleteById(coupon.getCouponId());
                deleteCouponSuccess();
            }
        }
    }


}
