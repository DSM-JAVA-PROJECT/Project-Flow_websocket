package com.projectflow.projectflowwebsocket.global.security.websocket;

import com.projectflow.projectflowwebsocket.global.auth.exceptions.InvalidTokenException;
import com.projectflow.projectflowwebsocket.global.security.httpsecurity.JwtTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StompInterceptor implements ChannelInterceptor {

    private static final String AUTH_HEADER = "Authorization";
    private static final String JWT_PREFIX = "Bearer ";
    private final JwtTokenValidator jwtTokenValidator;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {      // 메세지들이 통과하는 interceptor 로, send 이전에 거쳐간다.
        System.out.println("################# preSend 도착");
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);       // Message 에서 Header 추출
        String token = accessor.getFirstNativeHeader(AUTH_HEADER).replaceFirst(JWT_PREFIX, "");
        Authentication authentication = jwtTokenValidator.createAuthentication(token);
        boolean isConnect = StompCommand.CONNECT == accessor.getCommand();
        if (isConnect && authentication == null) {        // 만약 연결 요청이고 토큰이 유효하지 않다면
            SecurityContextHolder.clearContext();
            throw InvalidTokenException.EXCEPTION;
        } else {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return message;
    }
}
