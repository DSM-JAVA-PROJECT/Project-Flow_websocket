package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.project.entity.Project;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
public class CustomChatRoomRepositoryImpl implements CustomChatRoomRepository {

    private final MongoTemplate mongoTemplate;


    // TODO: 2021-09-17 프로젝트 레포지토리로 이동하렴
    @Override
    public boolean isProjectMember(User user, String projectId) {
        return mongoTemplate.exists(query(where("id").is(projectId)
                .andOperator(where("projectUsers").elemMatch(where("id").is(user.getId())))),
                Project.class);
    }

    @Override
    public String joinChatRoom(String chatRoomId, User user) {
        return mongoTemplate.upsert(query(where("_id").is(getObjectId(chatRoomId))),
                new Update().push("userIds", user),
                ChatRoom.class).getUpsertedId().asString().getValue();
    }

    @Override
    public boolean isChatRoomMember(String chatRoomId, User user) {
        return mongoTemplate.findOne(query(where("_id").is(getObjectId(chatRoomId))),
                ChatRoom.class) != null;
    }

    @Override
    public void deleteMember(String chatRoomId, String userId) {
//        mongoTemplate.remove(query(where("chat")))
    }

    private ObjectId getObjectId(String id) {
        return new ObjectId(id);
    }

}
