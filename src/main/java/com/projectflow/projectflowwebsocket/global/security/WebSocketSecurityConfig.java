package com.projectflow.projectflowwebsocket.global.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpSubscribeDestMatchers("/topic/**", "/queue/**").denyAll()                // 해당 uri 들은 client 가 요청을 보내는게 아니라 우리가 사용하기 때문에 닫아야 한다.
                .simpTypeMatchers(SimpMessageType.SUBSCRIBE, SimpMessageType.CONNECT).authenticated()  // 연결, MESSAGE, SUBSCRIBE(채팅방 입장 등)은 열어둔다.
                // MESSAGE 를 deny 하는 이유는 /topic/** 으로 직접 요청을 보낼 수 있기 때문에 기피해야 한다.
                .anyMessage().denyAll();
    }
}
