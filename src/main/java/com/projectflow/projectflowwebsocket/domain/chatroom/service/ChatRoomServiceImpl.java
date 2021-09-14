package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoomRepository;
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
    public String createChatRoom(String projectId, CreateChatRoomRequest request) {
        User user = authenticationFacade.getCurrentUser();
        chatRoomRepository.isProjectMember(user, projectId);
        chatRoomRepository.saveChatRoom(projectId, buildChatRoom(request, user));
        return "created";
    }

    private ChatRoom buildChatRoom(CreateChatRoomRequest request, User user) {
        return ChatRoom.builder()
                .name(request.getName())
                .userIds(List.of(user))
                .build();
    }

}
