package com.projectflow.projectflowwebsocket.domain;

import com.projectflow.projectflowwebsocket.domain.chat.entity.ChatRepository;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoom;
import com.projectflow.projectflowwebsocket.domain.chatroom.entity.ChatRoomRepository;
import com.projectflow.projectflowwebsocket.domain.project.entity.Project;
import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectRepository;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.domain.user.entity.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

public abstract class BasicTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ChatRoomRepository chatRoomRepository;

    @Autowired
    protected ProjectRepository projectRepository;

    @Autowired
    protected ChatRepository chatRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    protected User user;

    protected User user2;

    protected Project project;

    protected ChatRoom chatRoom;

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
        mongoTemplate.remove(new Query(), Project.class);
        mongoTemplate.remove(new Query(), ChatRoom.class);
        mongoTemplate.remove(new Query(), User.class);
    }
}
