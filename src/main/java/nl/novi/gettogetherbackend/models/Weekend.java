package nl.novi.gettogetherbackend.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Checked

@Entity
@Table(name = "weekends")

public class Weekend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull(message = "Time cannot be null")
    @Temporal(TemporalType.TIME)
    private String time;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    private Integer temperature;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;

    @OneToMany(mappedBy = "weekend", cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "weekend", cascade = CascadeType.ALL)
    private List<Activity> activities;

    public Weekend() {
    }

    public Weekend(String name, Date date, String time, String location, int temperature, User addedBy) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.temperature = temperature;
        this.addedBy = addedBy;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public List<Activity> getActivities() {
        return activities;
    }
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Long getAddedById() {
        return addedBy.getId();
    }
    public void setAddedById(Long id) {
        this.addedBy.setId(this.id);
    }

    public void setAddedBy(User user) {
        this.addedBy = user;
    }
    public User getAddedBy() {
        return addedBy;
    }

    public Set<Group> getGroups() {
        return groups;
    }
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

}
