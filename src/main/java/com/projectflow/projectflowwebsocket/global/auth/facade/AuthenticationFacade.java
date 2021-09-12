package com.projectflow.projectflowwebsocket.global.auth.facade;

import org.springframework.stereotype.Component;

@Component
public interface AuthenticationFacade {
    String getCurrentUser();
}
