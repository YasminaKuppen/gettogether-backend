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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        // Clean up previous data
        groupRepository.deleteAll();
        voteRepository.deleteAll();
        activityRepository.deleteAll();
        weekendRepository.deleteAll();
        userRepository.deleteAll();

        // Create and save User
        User user = new User("Yasmina",
                "yasmina@gettogether.nl",
                "$2a$10$d3u1KzyI93enqnsfRdZ3iuIZvbyvEeP94aIHhNAMSyisGt3sCgAUu");
        user = userRepository.save(user);
        System.out.println(user.getId());


        // Create and save Weekend
        Date date = new Date(2025, 12, 3);
        Weekend weekend = new Weekend(
                "test",
                date,
                "13:00",
                "Spring Boot",
                20,
                user);
        weekendRepository.save(weekend);


        // Create and save Group
        Group group = new Group(weekend, user);
        groupRepository.save(group);

        // Create and save Activity
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
        String voteRequest = """
                {
                    "activityId": 10,
                    "userId": 7
                }""";

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voteRequest))
                .andExpect(status().isCreated());
    }
}
