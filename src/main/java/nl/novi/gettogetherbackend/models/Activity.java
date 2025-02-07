package nl.novi.gettogetherbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

// Checked

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private String location;

    @Min(0)
    private Float costs;

    @ManyToOne
    @JoinColumn(name = "weekend_id", nullable = false)
    private Weekend weekend;

    @OneToMany(mappedBy = "activity")
    @JsonIgnore
    private List<Vote> votes;

    @OneToOne
    @JsonIgnoreProperties(value = {"contents", "contentsType"})
    Image image;

    public Activity() {
    }

    public Activity(String title, String description, String location, Float costs, Weekend weekend) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.costs = costs;
        this.weekend = weekend;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Float getCosts() {
        return costs;
    }
    public void setCosts(Float costs) {
        this.costs = costs;
    }

    public Weekend getWeekend() {
        return weekend;
    }
    public void setWeekend(Weekend weekend) {
        this.weekend = weekend;
    }

    public List<Vote> getVotes() {
        return votes;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

}
