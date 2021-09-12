package com.projectflow.projectflowwebsocket.domain.user.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Document(collation = "user")
public class User {

    @MongoId
    private String id;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private String profileImage;

    @NotBlank
    private String phoneNumber;
}
