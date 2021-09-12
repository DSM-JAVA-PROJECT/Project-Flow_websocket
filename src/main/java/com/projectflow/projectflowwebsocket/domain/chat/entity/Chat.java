package com.projectflow.projectflowwebsocket.domain.chat.entity;

import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Document(collation = "chat")
public class Chat {

    @MongoId
    private String id;

    @NotBlank
    private String message;

    @DBRef
    private User sender;

    @DBRef(lazy = true)
    private ChatRoom chatRoom;

    @DBRef(lazy = true)
    private List<User> receiver;

    @CreatedDate
    private LocalDateTime createdAt;

}