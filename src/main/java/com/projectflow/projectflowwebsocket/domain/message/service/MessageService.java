package com.projectflow.projectflowwebsocket.domain.message.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;

public interface MessageService {
    void sendCreateChatRoomMessage(String projectId, ChatRoom chatRoom);

    void sendJoinMessage(String chatRoomId);
}
