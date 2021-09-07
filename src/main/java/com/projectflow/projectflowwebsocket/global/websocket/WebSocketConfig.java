package com.projectflow.projectflowwebsocket.global.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${rabbit.url}")
    private String host;

    @Value("${rabbit.password}")
    private String password;

    @Value("${rabbit.port}")
    private int port;

    @Value("${rabbit.username}")
    private String username;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
//        registry.enableSimpleBroker("/topic");      // in memory topic 을 사용.
        registry.enableStompBrokerRelay("/topic")       // 외부 MQ 사용 설정
                .setRelayHost(host)
                .setRelayPort(port)
                .setClientLogin(username)
                .setClientPasscode(password);
    }

}
