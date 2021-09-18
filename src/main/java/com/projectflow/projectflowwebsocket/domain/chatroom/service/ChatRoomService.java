package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;

public interface ChatRoomService {
    ChatRoom createChatRoom(String projectId, CreateChatRoomRequest request);
    String joinChatRoom(String chatRoomId);
    void resignChatRoom(String chatRoomId);
}
