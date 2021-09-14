package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;

public interface ChatRoomService {
    int createChatRoom(String projectId, CreateChatRoomRequest request);

    int joinChatRoom(String chatRoomId);
}
