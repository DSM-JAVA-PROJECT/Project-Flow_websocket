package com.projectflow.projectflowwebsocket.global.security.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class JwtExpiredException extends GlobalException {
    public static final GlobalException EXCEPTION = new JwtExpiredException();
    private JwtExpiredException() {
        super(ErrorCode.JWT_VALIDATE_FAILED);
    }
}
