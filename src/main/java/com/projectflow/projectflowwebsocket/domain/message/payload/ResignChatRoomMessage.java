package com.projectflow.projectflowwebsocket.domain.message.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ResignChatRoomMessage {

    @Builder.Default
    private final MessageType messageType = MessageType.EXIT;

    private String userId;

}
