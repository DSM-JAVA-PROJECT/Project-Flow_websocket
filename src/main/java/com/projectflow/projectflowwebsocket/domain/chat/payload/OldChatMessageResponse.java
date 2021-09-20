package com.projectflow.projectflowwebsocket.domain.chat.payload;

import com.projectflow.projectflowwebsocket.domain.message.payload.MessageType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OldChatMessageResponse {

    @Builder.Default
    private MessageType messageType = MessageType.MESSAGE;

    private String id;

    private String message;

    private String senderName;

    private String senderImage;

    private boolean isMine;

    private List<String> readerList;

    private LocalDateTime cratedAt;

}
