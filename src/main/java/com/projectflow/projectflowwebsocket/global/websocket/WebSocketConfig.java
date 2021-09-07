package com.projectflow.projectflowwebsocket.global.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerAdapter;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class WebSocketConfig {

    public static final String ENDPOINT = "/socket";

    @Bean
    public HandlerAdapter handlerAdapter() {            // global websocket 설정함. Spring Webflux 에서 websocket 을 킴
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public HandlerMapping handlerMapping(WebSocketHandler webSocketHandler) {            // Spring 이 Socket 연결을 시작하기 위한 endpoint 설정

        Map<String, WebSocketHandler> map = Map.of(ENDPOINT, webSocketHandler);

        return new SimpleUrlHandlerMapping(map, -1);            // -1 이라는 우선순위로 등록
    }

}
