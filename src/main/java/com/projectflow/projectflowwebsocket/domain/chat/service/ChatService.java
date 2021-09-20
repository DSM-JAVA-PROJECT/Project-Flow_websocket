package com.projectflow.projectflowwebsocket.domain.chat.service;

import com.projectflow.projectflowwebsocket.domain.chat.payload.ChatRequest;
import com.projectflow.projectflowwebsocket.domain.chat.payload.OldChatMessageListResponse;
import org.springframework.data.domain.Pageable;

public interface ChatService {
    void saveMessage(String chatRoomId, ChatRequest request);
    void removeMessage(String chatId);
    OldChatMessageListResponse getOldChatMessage(String chatRoomId, Pageable pageable);
}
