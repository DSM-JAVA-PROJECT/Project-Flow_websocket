package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;

public interface CustomChatRoomRepository {

    boolean isProjectMember(User user, String projectId);

    String joinChatRoom(String chatRoomId, User user);

    boolean isChatRoomMember(String chatRoomId, User user);

    void deleteMember(String chatRoomId, String userId);

}
