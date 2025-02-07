package nl.novi.gettogetherbackend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testActivity() {

        //Arrange
        Activity activity = new Activity();
        Long expected_id = 1L;
        String expected_title = "vriendinnenweekend";
        String expected_description = "Leuke dag";
        String expected_location = "Den Bosch";
        Float expected_costs = 20F;

        //Act
        activity.setId(1L);
        activity.setTitle("vriendinnenweekend");
        activity.setDescription("Leuke dag");
        activity.setLocation("Den Bosch");
        activity.setCosts(20F);

        //Assert
        assertEquals(expected_id, activity.getId());
        assertEquals(expected_title, activity.getTitle());
        assertEquals(expected_description, activity.getDescription());
        assertEquals(expected_location, activity.getLocation());
        assertEquals(expected_costs, activity.getCosts());

    }

    @Test
    public void testArray() {

        //Arrange
        Activity activity = new Activity();
        List<Vote> expected_votes = new ArrayList<>();

        //Act
        activity.setVotes(expected_votes);

        //Assert
        assertEquals(expected_votes, activity.getVotes());

    }

    @Test
    void testConstructor() {

        //Arrange
        User user = new User();
        Weekend weekend = new Weekend();
        Activity activity = new Activity("test", "test", "test", 20F, weekend);

        //Act
        String titleTest = activity.getTitle();
        String descriptionTest = activity.getDescription();
        String locationTest = activity.getLocation();
        Float costsTest = activity.getCosts();
        Weekend weekendTest = activity.getWeekend();

        //Assert
        assertEquals("test", titleTest);
        assertEquals("test", descriptionTest);
        assertEquals("test", locationTest);
        assertEquals(20F, costsTest);
        assertEquals(weekend, weekendTest);

    }

    @Test
    public void testEntities(){

        //Arrange
        Activity activity = new Activity();
        User user = new User();
        Weekend weekend = new Weekend();
        Image image = new Image();

        //Act
        activity.setWeekend(weekend);
        activity.setImage(image);

        //Assert
        assertEquals(weekend, activity.getWeekend());
        assertEquals(image, activity.getImage());

    }

    @Test
    public void testTypes() {

        // Arrange
        Activity activity = new Activity();

        // Act
        activity.setId(1L);
        activity.setTitle("vriendinnenweekend");
        activity.setCosts(20F);

        // Assert
        assertInstanceOf(Long.class, activity.getId());
        assertInstanceOf(String.class, activity.getTitle());
        assertInstanceOf(Float.class, activity.getCosts());

    }

}
