package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository {
    void saveChatRoom(String projectId, ChatRoom chatRoom);
    boolean isProjectMember(User user, String projectId);
    void joinChatRoom(String chatRoomId, User user);
    boolean isChatRoomMember(String chatRoomId, User user);
}
