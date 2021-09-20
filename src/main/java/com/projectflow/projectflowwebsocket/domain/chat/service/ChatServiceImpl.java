package com.projectflow.projectflowwebsocket.domain.chat.service;

import com.projectflow.projectflowwebsocket.domain.chat.entity.Chat;
import com.projectflow.projectflowwebsocket.domain.chat.entity.ChatRepository;
import com.projectflow.projectflowwebsocket.domain.chat.exceptions.UserNotMessageOwnerException;
import com.projectflow.projectflowwebsocket.domain.chat.payload.ChatRequest;
import com.projectflow.projectflowwebsocket.domain.chat.payload.OldChatMessageListResponse;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoomRepository;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.ChatRoomNotFoundException;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.NotChatRoomMemberException;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.global.auth.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void saveMessage(String chatRoomId, ChatRequest request) {
        User user = authenticationFacade.getCurrentUser();

        validateChatRoom(chatRoomId, user);
        ChatRoom chatRoom = chatRoomRepository.findById(new ObjectId(chatRoomId))
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);

        List<User> receivers = chatRoom.getUserIds();
        receivers.remove(user);

        chatRepository.save(buildChat(chatRoom, user, request.getMessage()));
    }

    @Override
    public void removeMessage(String chatId) {
        User user = authenticationFacade.getCurrentUser();
        messageIsMine(chatId, user);
        chatRepository.deleteById(new ObjectId(chatId));
    }

    @Override
    public OldChatMessageListResponse getOldChatMessage(Long page) {
        return null;
    }

    private void validateChatRoom(String chatRoomId, User user) {
        if (!chatRoomRepository.isChatRoomMember(chatRoomId, user)) {
            throw NotChatRoomMemberException.EXCEPTION;
        }
    }

    private void messageIsMine(String chatId, User user) {
        if (chatRepository.findByIdAndSender(chatId, user).isEmpty()) {
            throw UserNotMessageOwnerException.EXCEPTION;
        }
    }

    private Chat buildChat(ChatRoom chatRoom, User user, String message) {
        List<User> receivers = chatRoom.getUserIds();
        receivers.remove(user);
        return Chat.builder()
                .receiver(receivers)
                .sender(user)
                .chatRoom(chatRoom)
                .message(message)
                .build();
    }
}
