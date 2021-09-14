package com.projectflow.projectflowwebsocket.global.auth.facade;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.domain.user.entity.UserRepository;
import com.projectflow.projectflowwebsocket.global.auth.exceptions.AuthUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(getEmail())
                .orElseThrow(() -> AuthUserNotFoundException.EXCEPTION);
    }

    @Override
    public String getCurrentEmail() {
        return getEmail();
    }

    private String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
