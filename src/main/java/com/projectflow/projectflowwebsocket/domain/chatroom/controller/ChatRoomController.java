package com.projectflow.projectflowwebsocket.domain.chatroom.controller;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import com.projectflow.projectflowwebsocket.domain.chatroom.service.ChatRoomService;
import com.projectflow.projectflowwebsocket.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    @MessageMapping("/create/chatroom/{projectId}")
    public int createChatRoom(@DestinationVariable String projectId,
                              @Payload CreateChatRoomRequest request) {
        chatRoomService.createChatRoom(projectId, request);
        return 201;
    }

    @MessageMapping("/join/chatroom/{chatRoomId}")
    public int joinChatRoom(@DestinationVariable String chatRoomId) {
        String roomId = chatRoomService.joinChatRoom(chatRoomId);
        messageService.sendJoinMessage(roomId);
        return 200;
    }

    @MessageMapping("/resign/chatroom/{chatRoomId}")
    public int resign(@DestinationVariable String chatRoomId) {
        chatRoomService.resignChatRoom(chatRoomId);
        messageService.sendResignMessage(chatRoomId);
        return 200;
    }

}
