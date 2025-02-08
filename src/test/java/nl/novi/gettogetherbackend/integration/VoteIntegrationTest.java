package nl.novi.gettogetherbackend.integration;

import nl.novi.gettogetherbackend.GettogetherBackendApplication;
import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = GettogetherBackendApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VoteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    WeekendRepository weekendRepository;


    @BeforeEach
    void setUp() {

        User user = new User("TestUser",
                "yasmina@gettogether.nl",
                "$2a$10$d3u1KzyI93enqnsfRdZ3iuIZvbyvEeP94aIHhNAMSyisGt3sCgAUu");
        user = userRepository.save(user);

        Date date = new Date(2025, 12, 3);
        Weekend weekend = new Weekend(
                "test",
                date,
                "13:00",
                "Spring Boot",
                20,
                user);
        weekendRepository.save(weekend);

        Group group = new Group(weekend);
        group.setId(weekend.getId());
        groupRepository.save(group);

        Activity testActivity = new Activity(
                "Paardrijden",
                "Rijden op een paard",
                "Manege",
                39.95F,
                weekend);
        activityRepository.save(testActivity);
    }

    @Test
    void testVote() throws Exception {

        Optional<User> optionalUser = userRepository.findByUsername("TestUser");
        User user = optionalUser.get();
        Long userId = user.getId();

        List<Activity> listActivity = activityRepository.findAll();
        int cnt = listActivity.size();
        Long activityId = listActivity.get(cnt-1).getId();


        String voteRequest = """
                {
                    "activityId": """+activityId+"""
                    ,
                    "userId": """+userId+"""
                }""";

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voteRequest))
                .andExpect(status().isCreated());
    }
}
