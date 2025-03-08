package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatHistoryResponse {

    List<ChatInfoResponse> todayChats;

    List<ChatInfoResponse> yesterdayChats;

    List<ChatInfoResponse> weekAgoChats;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ChatInfoResponse {
        Long chatId;

        String title;
    }
}
