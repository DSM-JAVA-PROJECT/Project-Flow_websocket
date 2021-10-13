//package com.projectflow.projectflowwebsocket.domain.chat.controller;
//
//import com.projectflow.projectflowwebsocket.domain.chat.payload.OldChatMessageListResponse;
//import com.projectflow.projectflowwebsocket.domain.chat.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class ChatRestController {
//
//    private final ChatService chatService;
//
//    @GetMapping("/chat/{chatRoomId}")
//    public OldChatMessageListResponse getOldMessages(@PathVariable String chatRoomId,
//                                                     Pageable pageable) {
//        chatService.getOldChatMessage(chatRoomId, pageable);
//        return chatService.getOldChatMessage(chatRoomId, pageable);
//    }
//}
