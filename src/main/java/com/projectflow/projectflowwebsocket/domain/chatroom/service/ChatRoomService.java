package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;

public interface ChatRoomService {
    void createChatRoom(String projectId, CreateChatRoomRequest request);
    void joinChatRoom(String chatRoomId);
    void resignChatRoom(String chatRoomId);
}
