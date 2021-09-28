package com.projectflow.projectflowwebsocket.domain.chat.service;

import com.projectflow.projectflowwebsocket.domain.chat.entity.Chat;
import com.projectflow.projectflowwebsocket.domain.chat.entity.ChatRepository;
import com.projectflow.projectflowwebsocket.domain.chat.exceptions.UserNotMessageOwnerException;
import com.projectflow.projectflowwebsocket.domain.chat.payload.ChatRequest;
import com.projectflow.projectflowwebsocket.domain.chat.payload.OldChatMessageListResponse;
import com.projectflow.projectflowwebsocket.domain.chat.payload.OldChatMessageResponse;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoomRepository;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.ChatRoomNotFoundException;
import com.projectflow.projectflowwebsocket.domain.chatroom.exceptions.NotChatRoomMemberException;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.global.auth.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public Chat saveMessage(String chatRoomId, ChatRequest request) {
        User user = authenticationFacade.getCurrentUser();

        validateChatRoom(chatRoomId, user);
        ChatRoom chatRoom = findChatRoomById(chatRoomId);

        List<User> receivers = chatRoom.getUserIds();
        receivers.remove(user);

        return chatRepository.save(buildChat(chatRoom, user, request.getMessage()));
    }

    @Override
    public String removeMessage(String chatId) {
        User user = authenticationFacade.getCurrentUser();
        Chat chat = messageIsMine(chatId, user);
        chatRepository.deleteById(new ObjectId(chatId));
        return chat.getChatRoom().getId().toString();
    }

    @Override
    public OldChatMessageListResponse getOldChatMessage(String chatRoomId, Pageable pageable) {
        User user = authenticationFacade.getCurrentUser();
        ChatRoom chatRoom = findChatRoomById(chatRoomId);
        return new OldChatMessageListResponse(
                chatRepository.findAllByChatRoomOrderByCreatedAtAsc(chatRoom, pageable)
                        .map(chat -> {
                            chat.getReceiver().remove(user);
                            return chat;
                        })
                        .map(chat -> buildResponse(chat, user)).getContent()
        );
    }

    private OldChatMessageResponse buildResponse(Chat chat, User user) {
        return OldChatMessageResponse.builder()
                .cratedAt(chat.getCreatedAt())
                .id(chat.getId().toString())
                .isMine(user.equals(chat.getSender()))
                .message(chat.getMessage())
                .readerList(chat.getReceiver().stream().map(User::getEmail).collect(Collectors.toList()))
                .senderImage(chat.getSender().getProfileImage())
                .senderName(chat.getSender().getName())
                .build();
    }

    private void validateChatRoom(String chatRoomId, User user) {
        if (!chatRoomRepository.isChatRoomMember(chatRoomId, user)) {
            throw NotChatRoomMemberException.EXCEPTION;
        }
    }

    private Chat messageIsMine(String chatId, User user) {
        return chatRepository.findByIdAndSender(new ObjectId(chatId), user)
                .orElseThrow(() -> UserNotMessageOwnerException.EXCEPTION);
    }

    private ChatRoom findChatRoomById(String chatRoomId) {
        return chatRoomRepository.findById(new ObjectId(chatRoomId))
                .orElseThrow(() -> ChatRoomNotFoundException.EXCEPTION);
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
