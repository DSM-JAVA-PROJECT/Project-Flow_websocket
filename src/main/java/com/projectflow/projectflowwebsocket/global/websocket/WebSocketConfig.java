package com.projectflow.projectflowwebsocket.global.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.port}")
    private int port;

    @Value("${rabbitmq.username}")
    private String username;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.setPathMatcher(new AntPathMatcher("."));       // endpoint 가 adf.asf.adf 와 같이 .으로 구분될 수 있도록 설정
//        registry.enableSimpleBroker("/topic");      // in memory topic 을 사용.
        registry.enableStompBrokerRelay("/topic")       // 외부 MQ 사용 설정
                .setRelayHost(host)
                .setRelayPort(61613)                                    // rabbitMQ 에서 STOMP plugin 포트는 61613
                .setClientLogin(username)
                .setClientPasscode(password);
    }

}
