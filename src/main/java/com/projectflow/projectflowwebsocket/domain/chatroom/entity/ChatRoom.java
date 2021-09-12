package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.plan.entity.Plan;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ChatRoom {

    @MongoId
    private String id;

    @NotBlank
    private String name;

    @DBRef(lazy = true)
    private List<User> userIds;

    @DBRef(lazy = true)
    private List<Plan> plans;

}
