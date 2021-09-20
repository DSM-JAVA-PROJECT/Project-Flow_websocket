package com.projectflow.projectflowwebsocket.domain.chat.entity;

import com.projectflow.projectflowwebsocket.domain.BasicTest;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class ChatRepositoryTest extends BasicTest {
    @Test
    void 채팅_저장() {
        Chat savedChat = chatRepository.save(Chat.builder()
                .message("test")
                .chatRoom(chatRoom)
                .sender(user)
                .receiver(List.of(user2))
                .build());
        assertThat(chatRepository.findById(savedChat.getId())).isPresent();
    }

    @Test
    void 단일_채팅_조회() {
        Chat savedChat = chatRepository.save(Chat.builder()
                .message("test")
                .chatRoom(chatRoom)
                .sender(user)
                .receiver(List.of(user2))
                .build());
        assertThat(chatRepository.findByIdAndSender(savedChat.getId(), user)).isPresent();
    }

    @Test
    void 단일_채팅_조회_실패() {
        Chat savedChat = chatRepository.save(Chat.builder()
                .message("test")
                .chatRoom(chatRoom)
                .sender(user)
                .receiver(List.of(user2))
                .build());
        assertThat(chatRepository.findByIdAndSender(savedChat.getId(), user2)).isEmpty();
    }
}