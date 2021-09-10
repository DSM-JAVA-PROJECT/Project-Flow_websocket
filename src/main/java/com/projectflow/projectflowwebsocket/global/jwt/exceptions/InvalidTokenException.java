package com.projectflow.projectflowwebsocket.global.jwt.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class InvalidTokenException extends GlobalException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
