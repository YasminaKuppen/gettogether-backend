package nl.novi.gettogetherbackend.integration;


import nl.novi.gettogetherbackend.GettogetherBackendApplication;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@ContextConfiguration(classes = GettogetherBackendApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private WeekendRepository weekendRepository;

    @BeforeEach
    void setUp() {


        List<User> testUsers = List.of(
                new User("Test_1",
                        "admin@gettogether.nl",
                        "$2a$10$8T3ol9KA/UD7kIA5g0tYwuSy3H9G8BhviFQnPa29Kqx9LHq4tqNwS"),
                new User("Test_2",
                        "yasmina@gettogether.nl",
                        "$2a$10$d3u1KzyI93enqnsfRdZ3iuIZvbyvEeP94aIHhNAMSyisGt3sCgAUu")
        );
        userRepository.saveAll(testUsers);
    }

    @Test
    void testLoginReturnsJwtToken() throws Exception {
        String loginRequest = """
            {
                "username": "Test_1",
                "password": "admin"
            }
            """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }


}
