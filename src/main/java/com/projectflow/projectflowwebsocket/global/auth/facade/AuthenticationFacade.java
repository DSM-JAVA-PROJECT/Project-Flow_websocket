package com.projectflow.projectflowwebsocket.global.auth.facade;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationFacade {
    User getCurrentUser();
}
