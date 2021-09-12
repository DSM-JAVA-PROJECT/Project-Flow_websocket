package com.projectflow.projectflowwebsocket.global.auth;

import com.projectflow.projectflowwebsocket.domain.user.entity.UserRepository;
import com.projectflow.projectflowwebsocket.global.auth.exceptions.AuthUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> AuthUserNotFoundException.EXCEPTION);
    }
}
