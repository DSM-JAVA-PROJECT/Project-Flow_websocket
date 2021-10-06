package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends CustomChatRoomRepository, MongoRepository<ChatRoom, ObjectId> {
}
