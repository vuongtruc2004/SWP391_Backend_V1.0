package com.configuration.redis;

import com.service.CampaignService;
import com.service.PurchaseService;
import com.util.enums.MessageStatusNotificationEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisExpirationEventListener implements MessageListener {

    private final PurchaseService purchaseService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final CampaignService campaignService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String redisKey = new String(message.getBody(), StandardCharsets.UTF_8);
        if (redisKey.startsWith("order:")) {
            Long orderId = Long.valueOf(redisKey.split(":")[1]);
            purchaseService.deleteOrder(orderId);
            simpMessagingTemplate.convertAndSend("/topic/purchased", MessageStatusNotificationEnum.ORDER_EXPIRED.toString());
        } else if (redisKey.startsWith("campaign:")) {
            Long campaignId = Long.valueOf(redisKey.split(":")[1]);
            campaignService.deleteCampaign(campaignId);
        }
    }
}
