package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectUser;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void saveChatRoom(String projectId, ChatRoom chatRoom) {
        mongoTemplate.updateFirst(query(where("project.id").is(projectId)), // project.id 가 projectId 인 Document 검색
                new Update().push("chatRooms").value(chatRoom),     // chatRooms 라는 collection 에 chatRoom Document 추가
                ChatRoom.class);
    }

    @Override
    public boolean isProjectMember(User user, String projectId) {
        return mongoTemplate.exists(query(where("project.id").is(projectId)
                        .elemMatch(where("projectUsers.userId").is(user.getId()))),
                ProjectUser.class);
    }

    @Override
    public void joinChatRoom(String chatRoomId, User user) {
        mongoTemplate.updateFirst(query(where("chatroom.id").is(chatRoomId)),
                new Update().push("userIds", user),
                User.class);
    }

    @Override
    public boolean isChatRoomMember(String chatRoomId, User user) {
        return mongoTemplate.exists(query(where("chatroom.id").is(chatRoomId)
                        .elemMatch(where("userIds.id").is(user.getId()))),
                ChatRoom.class);
    }

}
