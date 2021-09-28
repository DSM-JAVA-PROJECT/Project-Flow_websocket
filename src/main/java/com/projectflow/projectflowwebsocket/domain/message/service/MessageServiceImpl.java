package com.projectflow.projectflowwebsocket.domain.message.service;

import com.projectflow.projectflowwebsocket.domain.chat.entity.Chat;
import com.projectflow.projectflowwebsocket.domain.chat.payload.ChatRequest;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.message.payload.*;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.global.auth.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    @Override
    public void sendRemoveMessage(String chatRoomId, String chatId) {
        var message = RemoveChatMessage.builder()
                .chatId(chatId)
                .build();
        template.convertAndSend("/topic/chatroom/" + chatRoomId, message);
    }

    @Override
    public void sendMessage(String chatRoomId, Chat chat) {
        var message = ChatMessage.builder()
                .cratedAt(chat.getCreatedAt())
                .id(chat.getId().toString())
                .isMine(chat.getSender().getEmail().equals(authenticationFacade.getCurrentEmail()))
                .message(chat.getMessage())
                .readerList(chat.getReceiver().stream().map(User::getEmail).collect(Collectors.toList()))
                .senderImage(chat.getSender().getProfileImage())
                .senderName(chat.getSender().getName())
                .build();
        template.convertAndSend("/topic/chatroom/" + chatRoomId, message);

    }

    // TODO: 2021-09-21 채팅을 읽었을 때 보낼 메세지
    @Override
    public void sendReadMessage(String chatRoomId) {

    }

}
