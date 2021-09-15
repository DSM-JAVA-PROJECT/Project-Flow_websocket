package com.projectflow.projectflowwebsocket.domain.chatroom.controller;

import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import com.projectflow.projectflowwebsocket.domain.chatroom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @MessageMapping("/create/chatroom/{projectId}")
    public int createChatRoom(@DestinationVariable String projectId,
                              @Payload CreateChatRoomRequest request) {
        return chatRoomService.createChatRoom(projectId, request);
    }

    @MessageMapping("/join/chatroom/{chatRoomId}")
    public int createChatRoom(@DestinationVariable String chatRoomId) {
        return chatRoomService.joinChatRoom(chatRoomId);
    }

    @MessageMapping("/list/chatroom/{projectId}")
    public int chatRoomList(@DestinationVariable String chatRoomId) {
        return 0;
    }

}
