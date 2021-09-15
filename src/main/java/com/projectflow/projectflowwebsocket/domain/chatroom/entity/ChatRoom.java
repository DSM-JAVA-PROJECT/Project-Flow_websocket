package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.plan.entity.Plan;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collation = "chatroom")
public class ChatRoom {

    @MongoId
    private String id;

    @NotBlank
    private String name;

    @DBRef(lazy = true)
    private List<User> userIds;

    @DBRef(lazy = true)
    private List<Plan> plans;

    @Builder
    private ChatRoom(String name, List<User> userIds) {
        this.name = name;
        this.userIds = userIds;
        this.plans = new ArrayList<>();
    }
}
