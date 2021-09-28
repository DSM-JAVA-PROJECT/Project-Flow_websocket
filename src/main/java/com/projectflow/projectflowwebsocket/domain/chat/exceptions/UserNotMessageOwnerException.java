package com.projectflow.projectflowwebsocket.domain.chat.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class UserNotMessageOwnerException extends GlobalException {
    public static final GlobalException EXCEPTION = new UserNotMessageOwnerException();
    private UserNotMessageOwnerException() {
        super(ErrorCode.NOT_MESSAGE_OWNER);
    }
}
