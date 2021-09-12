package com.projectflow.projectflowwebsocket.global.auth.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class AuthUserNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new AuthUserNotFoundException();

    private AuthUserNotFoundException() {
        super(ErrorCode.AUTH_USER_NOT_FOUND);
    }
}
