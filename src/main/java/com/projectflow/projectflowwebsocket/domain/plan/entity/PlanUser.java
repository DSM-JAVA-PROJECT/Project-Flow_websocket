package com.projectflow.projectflowwebsocket.domain.plan.entity;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PlanUser {

    @DBRef(lazy = true)
    @Field(name = "userId")
    private User user;

    private boolean isFinished;

}
