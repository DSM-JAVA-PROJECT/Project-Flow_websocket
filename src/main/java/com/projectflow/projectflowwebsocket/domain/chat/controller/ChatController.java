package com.projectflow.projectflowwebsocket.domain.chat.controller;

import com.projectflow.projectflowwebsocket.domain.chat.entity.Chat;
import com.projectflow.projectflowwebsocket.domain.chat.payload.ChatRequest;
import com.projectflow.projectflowwebsocket.domain.chat.payload.OldChatMessageListResponse;
import com.projectflow.projectflowwebsocket.domain.chat.service.ChatService;
import com.projectflow.projectflowwebsocket.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final MessageService messageService;

    @MessageMapping("/chat/{chatRoomId}/send")
    public int sendMessage(@DestinationVariable String chatRoomId,
                           @Payload ChatRequest request) {
        Chat savedChat = chatService.saveMessage(chatRoomId, request);
        messageService.sendMessage(chatRoomId, savedChat);
        return 201;
    }

    @MessageMapping("/chat/remove/{chatId}")
    public int removeMessage(@DestinationVariable String chatId) {
        String chatRoomId = chatService.removeMessage(chatId);
        messageService.sendRemoveMessage(chatRoomId, chatId);
        return 204;
    }

}
