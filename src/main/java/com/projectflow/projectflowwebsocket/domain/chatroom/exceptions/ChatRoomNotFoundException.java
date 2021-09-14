package com.projectflow.projectflowwebsocket.domain.chatroom.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class ChatRoomNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new ChatRoomNotFoundException();
    private ChatRoomNotFoundException() {
        super(ErrorCode.CHATROOM_NOT_FOUND);
    }
}
