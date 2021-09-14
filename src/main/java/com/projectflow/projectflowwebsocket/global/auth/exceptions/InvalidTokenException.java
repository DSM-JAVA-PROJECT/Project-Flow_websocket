package com.projectflow.projectflowwebsocket.global.auth.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class InvalidTokenException extends GlobalException {
    public static final GlobalException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
