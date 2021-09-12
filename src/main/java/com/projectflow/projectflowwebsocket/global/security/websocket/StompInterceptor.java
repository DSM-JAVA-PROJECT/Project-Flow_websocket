package com.projectflow.projectflowwebsocket.global.security.websocket;

import com.projectflow.projectflowwebsocket.global.jwt.JwtValidator;
import com.projectflow.projectflowwebsocket.global.jwt.exceptions.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StompInterceptor implements ChannelInterceptor {

    private final JwtValidator jwtValidator;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {      // 메세지들이 통과하는 interceptor 로, send 이전에 거쳐간다.
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);       // Message 에서 Header 추출
        boolean isValid = jwtValidator.isValid(accessor.getFirstNativeHeader("Authorization"));
        boolean isConnect = StompCommand.CONNECT == accessor.getCommand();
        if (isConnect && !isValid) {        // 만약 연결 요청이고 토큰이 유효하지 않다면
            throw new InvalidTokenException();
        }
        return message;
    }
}
