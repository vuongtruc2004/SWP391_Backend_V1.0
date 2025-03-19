package com.configuration;

import com.entity.ChatEntity;
import com.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatConfig {

    private final ChatRepository chatRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void removeAllChatsBeforeLast7Days() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate weekAgo = LocalDate.now(zoneId).minusDays(7);
        Instant weekAgoInstant = weekAgo.atStartOfDay(zoneId).toInstant();

        List<ChatEntity> expireChats = chatRepository.findAllByUpdatedAtBefore(weekAgoInstant);
        chatRepository.deleteAll(expireChats);

        log.info("Đã xóa: " + expireChats.size() + " đoạn chat hết hạn!");
    }

}
