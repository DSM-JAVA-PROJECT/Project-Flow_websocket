package com.projectflow.projectflowwebsocket.domain.chatroom.controller;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.ChatRoomListResponse;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import com.projectflow.projectflowwebsocket.domain.chatroom.service.ChatRoomService;
import com.projectflow.projectflowwebsocket.domain.message.service.ChatRoomMessageService;
import com.projectflow.projectflowwebsocket.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatRoomMessageService messageService;

    @MessageMapping("/create/chatroom/{projectId}")
    public int createChatRoom(@DestinationVariable String projectId,
                              @Payload CreateChatRoomRequest request) {
        chatRoomService.createChatRoom(projectId, request);
        return 201;
    }

    @MessageMapping("/join/chatroom/{chatRoomId}")
    public int joinChatRoom(@DestinationVariable String chatRoomId) {
        ChatRoom chatRoom = chatRoomService.joinChatRoom(chatRoomId);
        messageService.sendJoinChatRoomMessage(chatRoom);
        return 200;
    }

    @MessageMapping("/resign/chatroom/{chatRoomId}")
    public int resign(@DestinationVariable String chatRoomId) {
        chatRoomService.resignChatRoom(chatRoomId);
        return 200;
    }

}
