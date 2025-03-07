package com.dto.request;

import com.util.enums.MessageSenderEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMessageRequest {

    Long chatId;

    List<MessageRequest> messages;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class MessageRequest {
        String content;

        @Enumerated(EnumType.STRING)
        MessageSenderEnum role;
    }
}
