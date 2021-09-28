package com.projectflow.projectflowwebsocket.domain.chat.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OldChatMessageListResponse {
    private List<OldChatMessageResponse> oldChatMessageResponses;
}
