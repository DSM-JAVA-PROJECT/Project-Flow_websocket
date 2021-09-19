package com.projectflow.projectflowwebsocket.domain.message.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;

public interface MessageService {
    void sendCreateChatRoomMessage(String projectId, ChatRoom chatRoom);

    void sendJoinMessage(String chatRoomId);

    void sendResignMessage(String chatRoomId);
}
