package com.projectflow.projectflowwebsocket.global.auth.facade;

import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.domain.user.entity.UserRepository;
import com.projectflow.projectflowwebsocket.global.auth.exceptions.AuthUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> AuthUserNotFoundException.EXCEPTION);
    }
}
