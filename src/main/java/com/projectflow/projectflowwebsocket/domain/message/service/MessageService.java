package com.projectflow.projectflowwebsocket.domain.message.service;

import com.projectflow.projectflowwebsocket.domain.chat.entity.Chat;
import com.projectflow.projectflowwebsocket.domain.chat.payload.ChatRequest;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;

public interface MessageService {
    void sendCreateChatRoomMessage(String projectId, ChatRoom chatRoom);

    void sendJoinMessage(String chatRoomId);

    void sendResignMessage(String chatRoomId);

    void sendRemoveMessage(String chatRoomId, String chatId);

    void sendMessage(String chatRoomId, Chat chat);

    void sendReadMessage(String chatRoomId);
}
