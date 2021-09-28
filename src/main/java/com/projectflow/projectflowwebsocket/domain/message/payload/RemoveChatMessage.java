package com.projectflow.projectflowwebsocket.domain.message.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveChatMessage {

    @Builder.Default
    private MessageType messageType = MessageType.REMOVE;

    private String chatId;

}
