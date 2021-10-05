package com.projectflow.projectflowwebsocket.domain.project.service;

import com.projectflow.projectflowwebsocket.domain.project.payload.ProjectMemberListResponse;

public interface MemberService {
    ProjectMemberListResponse getMemberList(String projectId);
}
