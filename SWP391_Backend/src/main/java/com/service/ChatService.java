package com.service;

import com.dto.request.CreateMessageRequest;
import com.dto.response.ChatResponse;
import com.entity.ChatEntity;
import com.entity.MessageEntity;
import com.entity.UserEntity;
import com.exception.custom.ChatException;
import com.exception.custom.UserException;
import com.helper.UserServiceHelper;
import com.repository.ChatRepository;
import com.util.enums.MessageSenderEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserServiceHelper userServiceHelper;
    private final ChatRepository chatRepository;
    private final ModelMapper modelMapper;

    public ChatResponse getLatestChatOfUser() {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        ChatEntity chatEntity = chatRepository.findTopByUser_UserIdOrderByCreatedAtDesc(userEntity.getUserId()).orElse(null);
        return chatEntity == null ? null : modelMapper.map(chatEntity, ChatResponse.class);
    }

    public ChatResponse addNewMessages(CreateMessageRequest createMessageRequest) {
        UserEntity userEntity = userServiceHelper.extractUserFromToken();
        if (userEntity == null) {
            throw new UserException("Vui lòng đăng nhập để thực hiện chức năng này!");
        }
        if (createMessageRequest == null || createMessageRequest.getMessages().isEmpty()) {
            throw new ChatException("Không có message!");
        }

        List<MessageEntity> inputMessages = new ArrayList<>();

        ChatEntity chatEntity = chatRepository.findById(createMessageRequest.getChatId()).orElse(null);
        if (chatEntity == null) {
            chatEntity = new ChatEntity();
            chatEntity.setMessages(new ArrayList<>());
        } else if (chatEntity.getMessages() == null) {
            chatEntity.setMessages(new ArrayList<>());
        }

        List<CreateMessageRequest.MessageRequest> messages = createMessageRequest.getMessages();
        CreateMessageRequest.MessageRequest userLastPrompt = null;
        for (int i = messages.size() - 1; i >= 0; i--) {
            if (messages.get(i).getRole().equals(MessageSenderEnum.USER) && userLastPrompt == null) {
                userLastPrompt = messages.get(i);
            }
            inputMessages.add(MessageEntity.builder()
                    .content(messages.get(messages.size() - 1 - i).getContent())
                    .role(messages.get(messages.size() - 1 - i).getRole())
                    .chat(chatEntity)
                    .build());
        }

        if (userLastPrompt == null) {
            throw new ChatException("Message không hợp lệ! Không có prompt của người dùng!");
        }

        String content = userLastPrompt.getContent();
        chatEntity.setTitle(content.length() > 100 ? content.substring(0, 100) + "..." : content);
        chatEntity.setUser(userEntity);
        chatEntity.getMessages().addAll(inputMessages);

        return modelMapper.map(chatRepository.save(chatEntity), ChatResponse.class);
    }
}
