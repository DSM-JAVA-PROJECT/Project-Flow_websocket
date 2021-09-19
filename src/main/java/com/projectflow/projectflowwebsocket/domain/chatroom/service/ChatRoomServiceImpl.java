package com.projectflow.projectflowwebsocket.domain.chatroom.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoomRepository;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.ChatRoomAlreadyParticipatedException;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.ChatRoomNotFoundException;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.NotChatRoomMemberException;
import com.projectflow.projectflowwebsocket.domain.chatroom.payload.CreateChatRoomRequest;
import com.projectflow.projectflowwebsocket.domain.project.entity.Project;
import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectRepository;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.global.auth.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ProjectRepository projectRepository;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    @Override
    public ChatRoom createChatRoom(String projectId, CreateChatRoomRequest request) {
        User user = authenticationFacade.getCurrentUser();
        if (!chatRoomRepository.isProjectMember(user, projectId)) {
            throw NotChatRoomMemberException.EXCEPTION;
        }
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);
        ChatRoom unsavedChatRoom = buildChatRoom(request, user);
        ChatRoom chatRoom = chatRoomRepository.save(unsavedChatRoom);
        project.getChatRooms().add(chatRoom);
        projectRepository.save(project);
        return chatRoom;
    }

    @Override
    public String joinChatRoom(String chatRoomId) {
        User user = authenticationFacade.getCurrentUser();
        if (chatRoomRepository.isChatRoomMember(chatRoomId, user)) {
            throw ChatRoomAlreadyParticipatedException.EXCEPTION;
        }
        return chatRoomRepository.joinChatRoom(chatRoomId, user);
    }

    @Override
    public void resignChatRoom(String chatRoomId) {
        User user = authenticationFacade.getCurrentUser();
        if (!chatRoomRepository.isChatRoomMember(chatRoomId, user)) {
            throw NotChatRoomMemberException.EXCEPTION;
        }
//        chatRoomRepository.delete
    }

    private ChatRoom buildChatRoom(CreateChatRoomRequest request, User user) {
        return ChatRoom.builder()
                .name(request.getName())
                .userIds(List.of(user))
                .build();
    }

}
