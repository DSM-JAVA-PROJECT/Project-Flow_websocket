package com.projectflow.projectflowwebsocket.domain.project.controller;

import com.projectflow.projectflowwebsocket.domain.chatroom.payload.ChatRoomListResponse;
import com.projectflow.projectflowwebsocket.domain.chatroom.service.ChatRoomService;
import com.projectflow.projectflowwebsocket.domain.project.payload.ProjectMemberListResponse;
import com.projectflow.projectflowwebsocket.domain.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{projectId}/member")
public class MemberController {

    private final MemberService memberService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/{projectId}")
    private ChatRoomListResponse getChatRoom(@PathVariable String projectId) {
        return chatRoomService.getChatRooms(projectId);
    }

    @GetMapping
    public ProjectMemberListResponse getMemberList(@PathVariable String projectId) {
        return memberService.getMemberList(projectId);
    }

    @GetMapping("/{chatRoomId}")
    public ProjectMemberListResponse getNotParticipatedMemberList(@PathVariable String projectId, @PathVariable String chatRoomId) {
        return memberService.getNotParticipatedMemberList(projectId, chatRoomId);
    }

}
