package com.projectflow.projectflowwebsocket.domain.message.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadMessage {

    @Builder.Default
    private MessageType messageType = MessageType.READ;

    private String email;

}
