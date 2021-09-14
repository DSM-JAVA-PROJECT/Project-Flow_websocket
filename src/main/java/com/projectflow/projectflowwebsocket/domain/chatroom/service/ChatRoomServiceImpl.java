package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoomRepository;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.ChatRoomAlreadyParticipatedException;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.NotChatRoomMemberException;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.global.auth.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public int createChatRoom(String projectId, CreateChatRoomRequest request) {
        User user = authenticationFacade.getCurrentUser();
        if(!chatRoomRepository.isProjectMember(user, projectId)) {
            throw NotChatRoomMemberException.EXCEPTION;
        }
        chatRoomRepository.saveChatRoom(projectId, buildChatRoom(request, user));
        return 201;
    }

    @Override
    public int joinChatRoom(String chatRoomId) {
        User user = authenticationFacade.getCurrentUser();
        if(chatRoomRepository.isChatRoomMember(chatRoomId, user)) {
            throw ChatRoomAlreadyParticipatedException.EXCEPTION;
        }
        chatRoomRepository.joinChatRoom(chatRoomId, user);
        return 201;
    }

    private ChatRoom buildChatRoom(CreateChatRoomRequest request, User user) {
        return ChatRoom.builder()
                .name(request.getName())
                .userIds(List.of(user))
                .build();
    }

}
