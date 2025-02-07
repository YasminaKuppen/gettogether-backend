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

    private Long userId;
    private Long activityId;
    private Long groupId;
    private Long weekendId;

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
        weekend = weekendRepository.save(weekend);
        weekendId = weekend.getId();  // Save the ID

        // Create and save Group
        Group group = new Group(weekend);
        group.addUser(user);
        group = groupRepository.save(group);
        groupId = group.getId();  // Save the ID

        // Create and save Activity
        Activity testActivity = new Activity(
                "Paardrijden",
                "Rijden op een paard",
                "Manege",
                39.95F,
                weekend);
        testActivity = activityRepository.save(testActivity);
        activityId = testActivity.getId();  // Save the ID
        System.out.println(testActivity.getId());
    }

    @Test
    void testVote() throws Exception {
        // JSON payload for creating the vote using the actual IDs
        String voteRequest = """
                {
                    "activityId": 10,
                    "userId": 7
                }""";

        // Perform the POST request and assert the response
        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voteRequest))
                .andExpect(status().isCreated());  // Check if response is 201
    }
}
