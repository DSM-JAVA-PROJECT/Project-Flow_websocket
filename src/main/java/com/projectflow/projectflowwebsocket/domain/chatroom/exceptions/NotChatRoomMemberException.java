package com.projectflow.projectflowwebsocket.domain.chatroom.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class NotChatRoomMemberException extends GlobalException {
    public static final GlobalException EXCEPTION = new NotChatRoomMemberException();
    private NotChatRoomMemberException() {
        super(ErrorCode.NOT_CHATROOM_MEMBER);
    }
}
