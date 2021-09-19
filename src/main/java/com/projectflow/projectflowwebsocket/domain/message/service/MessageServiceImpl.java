package com.projectflow.projectflowwebsocket.domain.message.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.message.payload.CreateChatRoomMessage;
import com.projectflow.projectflowwebsocket.domain.message.payload.JoinChatRoomMessage;
import com.projectflow.projectflowwebsocket.domain.message.payload.ResignChatRoomMessage;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.global.auth.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final SimpMessagingTemplate template;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void sendCreateChatRoomMessage(String projectId, ChatRoom chatRoom) {
        var message = CreateChatRoomMessage.builder()
                .id(chatRoom.getId().toString())
                .roomName(chatRoom.getName())
                .build();
        chatRoom.getUserIds()
                .forEach(user -> template.convertAndSendToUser(user.getEmail(), "/topic/chatroom/" + projectId, message));
    }

    @Override
    public void sendJoinMessage(String chatRoomId) {
        var message = JoinChatRoomMessage.builder()
                .userId(authenticationFacade.getCurrentEmail())
                .build();
        template.convertAndSend("/topic/chatroom/" + chatRoomId, message);
    }

    @Override
    public void sendResignMessage(String chatRoomId) {
        var message = ResignChatRoomMessage.builder()
                .userId(authenticationFacade.getCurrentEmail())
                .build();
        template.convertAndSend("/topic/chatroom/" + chatRoomId, message);
    }

}
