package com.projectflow.projectflowwebsocket.domain.project.service;

import com.projectflow.projectflowwebsocket.domain.project.entity.Project;
import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectRepository;
import com.projectflow.projectflowwebsocket.domain.project.exceptions.ProjectNotFoundException;
import com.projectflow.projectflowwebsocket.domain.project.payload.ProjectMemberListResponse;
import com.projectflow.projectflowwebsocket.domain.project.payload.ProjectMemberResponse;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectMemberListResponse getMemberList(String projectId) {
        Project project = projectRepository.findById(new ObjectId(projectId))
                .orElseThrow(() -> ProjectNotFoundException.EXCEPTION);

        return new ProjectMemberListResponse(project.getProjectUsers()
                .stream().map(projectUser -> ProjectMemberResponse.builder()
                        .id(projectUser.getUser().getId().toString())
                        .name(projectUser.getUser().getName())
                        .profileImage(projectUser.getUser().getProfileImage())
                        .build())
                .collect(Collectors.toList()));
    }
}
