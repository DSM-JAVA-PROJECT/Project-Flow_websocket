package com.projectflow.projectflowwebsocket.domain.chatroom.exceptions;

import com.projectflow.projectflowwebsocket.global.exception.ErrorCode;
import com.projectflow.projectflowwebsocket.global.exception.GlobalException;

public class NotChatRoomMemberException extends GlobalException {
    public static GlobalException EXCEPTION = new NotChatRoomMemberException();
    private NotChatRoomMemberException() {
        super(ErrorCode.USER_NOT_MEMBER);
    }
}
