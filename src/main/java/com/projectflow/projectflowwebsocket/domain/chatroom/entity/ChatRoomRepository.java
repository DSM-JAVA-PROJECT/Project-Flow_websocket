package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends CustomChatRoomRepository, MongoRepository<ChatRoom, String> {
}
