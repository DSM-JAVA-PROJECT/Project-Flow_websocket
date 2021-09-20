package com.projectflow.projectflowwebsocket.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    AUTH_USER_NOT_FOUND(404, "Auth User Not Found"),
    NOT_CHATROOM_MEMBER(403, "User Not Member of Project"),
    ALREADY_PARTICIPATE(409, "User Already Participated"),
    NOT_PROJECT_MEMBER(403, "Not Project Member"),
    CHATROOM_NOT_FOUND(404, "Chat Room Not Found"),
    INVALID_TOKEN(401, "Invalid Token");

    private final int status;
    private final String message;
}
