package com.projectflow.projectflowwebsocket.domain.project.service;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoomRepository;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.ChatRoomNotFoundException;
import com.projectflow.projectflowwebsocket.domain.project.entity.Project;
import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectRepository;
import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectUser;
import com.projectflow.projectflowwebsocket.domain.project.exceptions.ProjectNotFoundException;
import com.projectflow.projectflowwebsocket.domain.project.payload.ProjectMemberListResponse;
import com.projectflow.projectflowwebsocket.domain.project.payload.ProjectMemberResponse;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final ProjectRepository projectRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ProjectMemberListResponse getMemberList(String projectId) {
        Project project = getProject(projectId);

        return new ProjectMemberListResponse(project.getProjectUsers()
                .stream()
                .map(ProjectUser::getUser)
                .map(this::buildMemberResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public ProjectMemberListResponse getNotParticipatedMemberList(String projectId, String chatRoomId) {
        ChatRoom chatRoom = getChatRoom(chatRoomId);
        Project project = getProject(projectId);
        return new ProjectMemberListResponse(chatRoom.getUserIds()
                .stream()
                .filter(projectUser -> project.getProjectUsers().stream()
                        .anyMatch(member -> member.getUser().getEmail()
                                .equals(projectUser.getEmail())))
                .map(this::buildMemberResponse)
                .collect(Collectors.toList()));
    }

    private ChatRoom getChatRoom(String chatRoomId) {
        return chatRoomRepository.findById(new ObjectId(chatRoomId))
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);
    }

    private ProjectMemberResponse buildMemberResponse(User user) {
        return ProjectMemberResponse.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .build();
    }

    private Project getProject(String projectId) {
        return projectRepository.findById(new ObjectId(projectId))
                .orElseThrow(() -> ProjectNotFoundException.EXCEPTION);
    }
}
