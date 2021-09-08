package com.projectflow.projectflowwebsocket.global.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String CHAT_QUEUE = "chat.queue";
    public static final String CHAT_EXCHANGE = "chat.exchange";
    public static final String ROUTING_KEY = "room.*";

    @Bean
    Queue queue() {
        return new Queue(CHAT_QUEUE, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(CHAT_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    ConnectionFactory connectionFactory() {     // connectionFactory 를 caching 한 형태로 변경
        return new CachingConnectionFactory();
    }

}
