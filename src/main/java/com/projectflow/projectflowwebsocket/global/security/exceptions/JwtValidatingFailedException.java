package com.projectflow.projectflowwebsocket.global.security.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class JwtValidatingFailedException extends GlobalException {
    public static final GlobalException EXCEPTION = new JwtValidatingFailedException();
    private JwtValidatingFailedException() {
        super(ErrorCode.JWT_VALIDATE_FAILED);
    }
}
