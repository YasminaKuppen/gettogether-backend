package nl.novi.gettogetherbackend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @BeforeEach
    void setUp() {
    }

    @Test void testUser() {

        //Arrange
        User user = new User();

        //Act
        user.setId(1L);
        user.setUsername("user");
        user.setEmail("email@email.com");
        user.setPassword("password");
        user.setRole("ADMIN");

        //Assert
        assertEquals(1L, user.getId());
        assertEquals("user", user.getUsername());
        assertEquals("email@email.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("ADMIN", user.getRole());

    }

    @Test
    public void testConstructor() {

        //Arrange
        User user = new User("test", "email@email.com", "password");

        //Act
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        //Assert
        assertEquals("test", username);
        assertEquals("email@email.com", email);
        assertEquals("password", password);
    }

    @Test
    void testUserDetails() {

        //Arrange
        User user = new User("user", "email@email.com", "password");

        //Act
        boolean notExpired = user.isAccountNonExpired();
        boolean notLocked = user.isAccountNonLocked();
        boolean enabled = user.isEnabled();
        boolean notCredExpired = user.isCredentialsNonExpired();

        //Assert
        assertTrue(notExpired);
        assertTrue(notLocked);
        assertTrue(enabled);
        assertTrue(notCredExpired);

    }

    @Test
    void testUserCollection() {

        //Arrange
        User user = new User();
        List<GrantedAuthority> expected = new ArrayList<>();

        //Act
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        //Assert
        assertEquals(expected, authorities);

    }

    @Test
    public void testGroups() {

        //Arrange
        User user = new User();
        Set<Group> expectedGroups = new HashSet<>();
        expectedGroups.add(new Group());

        //Act
        user.setGroups(expectedGroups);
        Set<Group> actualGroups = user.getGroups();

        //Assert
        assertNotNull(actualGroups);
        assertEquals(expectedGroups, actualGroups);

    }

}
