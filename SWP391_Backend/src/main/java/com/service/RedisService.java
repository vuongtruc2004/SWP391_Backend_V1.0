package com.service;

import com.entity.CouponEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveCouponToRedis(CouponEntity coupon) {
        long ttl = Duration.between(Instant.now(), coupon.getEndTime()).getSeconds();

        if (ttl > 0) {
            redisTemplate.opsForValue().set("coupon:" + coupon.getCouponId(), coupon.getCouponCode(), ttl, TimeUnit.SECONDS);
            log.info("Đã lưu coupon vào redis!");
        }
    }
}
