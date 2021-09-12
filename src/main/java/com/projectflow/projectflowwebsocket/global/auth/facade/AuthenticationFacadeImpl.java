package com.projectflow.projectflowwebsocket.global.auth.facade;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
