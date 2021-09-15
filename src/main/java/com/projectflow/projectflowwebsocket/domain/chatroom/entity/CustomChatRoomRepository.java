package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomChatRoomRepository {

    boolean isProjectMember(User user, String projectId);

    void joinChatRoom(String chatRoomId, User user);

    boolean isChatRoomMember(String chatRoomId, User user);
}
