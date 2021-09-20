package com.projectflow.projectflowwebsocket.domain.chat.entity;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, ObjectId> {
    Optional<Chat> findByIdAndSender(String chatId, User user);
}
