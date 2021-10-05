package com.projectflow.projectflowwebsocket.domain.message.service;

import com.projectflow.projectflowwebsocket.domain.chat.entity.Chat;

public interface MessageService {
    void sendJoinMessage(String chatRoomId);

    void sendResignMessage(String chatRoomId);

    void sendRemoveMessage(String chatRoomId, String chatId);

    void sendMessage(String chatRoomId, Chat chat);

    void sendReadMessage(String chatRoomId);
}
