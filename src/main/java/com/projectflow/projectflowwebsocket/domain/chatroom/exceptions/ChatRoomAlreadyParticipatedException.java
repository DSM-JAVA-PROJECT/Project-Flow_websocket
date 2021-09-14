package com.projectflow.projectflowwebsocket.domain.chatroom.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class ChatRoomAlreadyParticipatedException extends GlobalException {
    public static final GlobalException EXCEPTION = new ChatRoomAlreadyParticipatedException();
    private ChatRoomAlreadyParticipatedException() {
        super(ErrorCode.ALREADY_PARTICIPATE);
    }
}
