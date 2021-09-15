package com.projectflow.projectflowwebsocket.domain.chatroom.entity;

import com.projectflow.projectflowwebsocket.domain.project.entity.Project;
import com.projectflow.projectflowwebsocket.domain.project.entity.ProjectRepository;
import com.projectflow.projectflowwebsocket.domain.user.entity.User;
import com.projectflow.projectflowwebsocket.domain.user.entity.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class ChatRoomRepositoryTest {

    @Autowired
    public ChatRoomRepository chatRoomRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public MongoTemplate mongoTemplate;

    public User user;
    public Project project;

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
        project = projectRepository.save(Project.builder()
                .endDate(LocalDate.now().plusDays(10))
                .startDate(LocalDate.now())
                .pm(user)
                .explanation("sdf")
                .logoImage("dsf")
                .title("title")
                .build());
    }

    @Test
    void 저장_성공_테스트() {
        final ChatRoom chatRoom = ChatRoom.builder()
                .userIds(List.of(user))
                .name("name")
                .build();
        ChatRoom chatRoom1 = chatRoomRepository.save(chatRoom);
        project.getChatRooms().add(chatRoom);
        projectRepository.save(project);
        assertThat(chatRoomRepository.findById(chatRoom1.getId())).isNotNull();
    }

}