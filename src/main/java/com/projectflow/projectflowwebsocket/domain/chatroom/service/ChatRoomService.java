package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import org.springframework.stereotype.Service;

@Service
public interface ChatRoomService {
    String createChatRoom(String projectId, CreateChatRoomRequest request);
}
