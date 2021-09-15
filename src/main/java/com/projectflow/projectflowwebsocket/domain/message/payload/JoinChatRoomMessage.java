package com.projectflow.projectflowwebsocket.domain.message.payload;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class JoinChatRoomMessage {

    @Builder.Default
    private final MessageType messageType = MessageType.JOIN;

    private String userId;

}
