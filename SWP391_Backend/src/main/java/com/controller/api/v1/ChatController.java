package com.controller.api.v1;

import com.dto.request.CreateMessageRequest;
import com.dto.response.ChatHistoryResponse;
import com.dto.response.ChatResponse;
import com.service.ChatService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
public class ChatController {

    private final ChatService chatService;

    @ApiMessage("Lấy đoạn chat theo ID thành công!")
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponse> getChatOfUserByChatId(@PathVariable Long chatId) {
        return ResponseEntity.ok(chatService.getChatOfUserByChatId(chatId));
    }

    @DeleteMapping("/{chatId}")
    public void deleteChatOfUserByChatId(@PathVariable Long chatId) {
        chatService.deleteChatOfUserByChatId(chatId);
    }

    @DeleteMapping("/all")
    public void deleteAllChatsOfUser() {
        chatService.deleteAllChatsOfUser();
    }

    @ApiMessage("Lấy lịch sử chat trong vòng 7 ngày của người dùng thành công!")
    @GetMapping("/history")
    public ResponseEntity<ChatHistoryResponse> getHistoryChatsOfUserWithin7Days(Pageable pageable) {
        return ResponseEntity.ok(chatService.getHistoryChatsOfUserWithin7Days(pageable));
    }

    @ApiMessage("Thêm các message vào đoạn chat thành công!")
    @PostMapping
    public ResponseEntity<ChatResponse> addNewMessages(@RequestBody CreateMessageRequest createMessageRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.addNewMessages(createMessageRequest));
    }
}
