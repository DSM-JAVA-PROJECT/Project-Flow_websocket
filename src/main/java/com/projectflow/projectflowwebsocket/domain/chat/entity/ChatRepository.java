package com.projectflow.projectflowwebsocket.domain.chat.entity;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, ObjectId> {
    Optional<Chat> findByIdAndSender(ObjectId chatId, User user);

    Page<Chat> findAllByChatRoomOrderByCreatedAtAsc(ChatRoom chatRoom, Pageable pageable);
}
