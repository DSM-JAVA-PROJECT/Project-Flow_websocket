package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.project.entity.Project;
import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectRepository;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.domain.user.entity.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class ChatRoomRepositoryTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private User user;
    private User user2;
    private Project project;
    private ChatRoom chatRoom;

    @BeforeEach
    void setUp() {
        user = userRepository.save(
                User.builder()
                        .email("email")
                        .name("name")
                        .phoneNumber("phoneNumber")
                        .profileImage("sadf")
                        .password("pwd")
                        .build()
        );
        user2 = userRepository.save(
                User.builder()
                        .email("email2")
                        .name("name")
                        .phoneNumber("phoneNumber")
                        .profileImage("sadf")
                        .password("pwd")
                        .build()
        );
        project = Project.builder()
                .endDate(LocalDate.now().plusDays(10))
                .startDate(LocalDate.now())
                .pm(user)
                .explanation("sdf")
                .logoImage("dsf")
                .title("title")
                .build();

        chatRoom = chatRoomRepository.save(ChatRoom.builder()
                .userIds(List.of(user))
                .name("name")
                .build());
        project.getChatRooms().add(chatRoom);
        projectRepository.save(project);
    }

    @AfterEach
    void cleanUp() {
//        mongoTemplate.remove(new Query(), Project.class);
//        mongoTemplate.remove(new Query(), ChatRoom.class);
//        mongoTemplate.remove(new Query(), User.class);
    }

    @Test
    void 저장_성공_테스트() {
        ChatRoom chatRoom1 = chatRoomRepository.save(ChatRoom.builder()
                .userIds(List.of(user))
                .name("name")
                .build());
        assertThat(chatRoomRepository.findById(chatRoom1.getId())).isPresent();
    }

    @Test
    void 미참가_여부_테스트() {
        mongoTemplate.findAll(ChatRoom.class)
                .forEach(chatRoom1 -> System.out.println(chatRoom1.getId()));
        assertThat(chatRoomRepository.isChatRoomMember(chatRoom.getId(), user)).isFalse();
    }

    @Test
    void 참가_테스트() {
        System.out.println("hi");
        assertThat(chatRoomRepository.isChatRoomMember(chatRoom.getId(), user)).isTrue();
    }

}