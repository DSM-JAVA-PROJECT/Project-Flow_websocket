package com.projectflow.projectflowwebsocket.domain.chatroom.controller;

import com.projectflow.projectflowwebsocket.domain.chatroom.payload.ChatRoomListResponse;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import com.projectflow.projectflowwebsocket.domain.chatroom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @MessageMapping("/create/chatroom/{projectId}")
    public int createChatRoom(@DestinationVariable String projectId,
                              @Payload CreateChatRoomRequest request) {
        chatRoomService.createChatRoom(projectId, request);
        return 201;
    }

    @MessageMapping("/join/chatroom/{chatRoomId}")
    public int joinChatRoom(@DestinationVariable String chatRoomId) {
        chatRoomService.joinChatRoom(chatRoomId);
        return 200;
    }

    @MessageMapping("/resign/chatroom/{chatRoomId}")
    public int resign(@DestinationVariable String chatRoomId) {
        chatRoomService.resignChatRoom(chatRoomId);
        return 200;
    }

    @GetMapping("/{projectId}")
    private ChatRoomListResponse getChatRoom(@PathVariable String projectId) {
        return chatRoomService.getChatRooms(projectId);
    }

}
