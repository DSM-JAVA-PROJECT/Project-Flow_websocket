package com.projectflow.projectflowwebsocket.domain.chatroom.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class NotProjectMemberException extends GlobalException {
    public static final GlobalException EXCEPTION = new NotProjectMemberException();
    private NotProjectMemberException() {
        super(ErrorCode.NOT_PROJECT_MEMBER);
    }
}
