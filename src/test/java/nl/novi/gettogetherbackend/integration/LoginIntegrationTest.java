package nl.novi.gettogetherbackend.integration;


import nl.novi.gettogetherbackend.GettogetherBackendApplication;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.repositories.*;
import nl.novi.gettogetherbackend.services.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private GroupRepository groupRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private WeekendRepository weekendRepository;

    @BeforeEach
    void setUp() {
        weekendRepository.deleteAll();
        activityRepository.deleteAll();
        voteRepository.deleteAll();
        groupRepository.deleteAll();
        userRepository.deleteAll();

        List<User> testUsers = List.of(
                new User("Admin",
                        "admin@gettogether.nl",
                        "$2a$10$8T3ol9KA/UD7kIA5g0tYwuSy3H9G8BhviFQnPa29Kqx9LHq4tqNwS"),
                new User("Yasmina",
                        "yasmina@gettogether.nl",
                        "$2a$10$d3u1KzyI93enqnsfRdZ3iuIZvbyvEeP94aIHhNAMSyisGt3sCgAUu")
        );
        userRepository.saveAll(testUsers);
    }

    @Test
    void testLoginReturnsJwtToken() throws Exception {
        // JSON payload for login
        String loginRequest = """
            {
                "username": "Admin",
                "password": "admin"
            }
            """;

        mockMvc.perform(post("/login")  // Change to your actual login endpoint
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status().isOk())  // Check if response is 200 OK
                .andExpect(jsonPath("$.token").exists()); // Ensure that a JWT token is present in the response
    }


}
