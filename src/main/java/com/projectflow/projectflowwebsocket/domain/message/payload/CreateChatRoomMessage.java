package com.projectflow.projectflowwebsocket.domain.message.payload;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreateChatRoomMessage {

    @Builder.Default
    private MessageType messageType = MessageType.CREATE;

    private String id;

    private String roomName;

}
