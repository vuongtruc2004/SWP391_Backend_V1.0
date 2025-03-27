package com.service;

import com.entity.CampaignEntity;
import com.entity.OrderEntity;
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

    public void saveOrderToRedis(OrderEntity order) {
        String redisKey = "order:" + order.getOrderId();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
            redisTemplate.delete(redisKey);
        }

        long ttl = Duration.between(Instant.now(), order.getExpiredAt()).toSeconds();

        if (ttl > 0) {
            redisTemplate.opsForValue().set(redisKey, order.getOrderCode(), ttl, TimeUnit.SECONDS);
            log.info("Save order to redis!");
        }
    }

    public void removeOrderInRedis(OrderEntity order) {
        String redisKey = "order:" + order.getOrderId();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
            redisTemplate.delete(redisKey);
        }
    }

    public void saveCampaignToRedis(CampaignEntity campaign) {
        String redisKey = "campaign:" + campaign.getCampaignId();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
            redisTemplate.delete(redisKey);
        }

        long ttl = Duration.between(Instant.now(), campaign.getEndTime()).toSeconds();

        if (ttl > 0) {
            redisTemplate.opsForValue().set(redisKey, campaign.getCampaignName(), ttl, TimeUnit.SECONDS);
            log.info("Save campaign to redis!");
        }
    }

    public void removeCampaignFromRedis(CampaignEntity campaign) {
        String redisKey = "campaign:" + campaign.getCampaignId();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
            redisTemplate.delete(redisKey);
        }
    }
}
