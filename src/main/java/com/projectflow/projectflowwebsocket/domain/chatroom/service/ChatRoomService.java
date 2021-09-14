package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import org.springframework.stereotype.Service;

@Service
public interface ChatRoomService {
    int createChatRoom(String projectId, CreateChatRoomRequest request);
    int joinChatRoom(String chatRoomId);
}
