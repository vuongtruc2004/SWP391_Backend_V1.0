package com.controller.api.v1;

import com.dto.request.CreateMessageRequest;
import com.dto.response.ChatResponse;
import com.service.ChatService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
public class ChatController {

    private final ChatService chatService;

    @ApiMessage("Lấy đoạn chat gần nhất thành công!")
    @GetMapping("/latest")
    public ResponseEntity<ChatResponse> getLatestChatOfUser() {
        return ResponseEntity.ok(chatService.getLatestChatOfUser());
    }

    @ApiMessage("Thêm các message vào đoạn chat thành công!")
    @PostMapping
    public ResponseEntity<ChatResponse> addNewMessages(@RequestBody CreateMessageRequest createMessageRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.addNewMessages(createMessageRequest));
    }
}
