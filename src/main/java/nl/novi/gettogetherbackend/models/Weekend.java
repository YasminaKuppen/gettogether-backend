package nl.novi.gettogetherbackend.models;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "weekends")

public class Weekend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Date cannot be null")
    private Date date;
    @NotNull(message = "Time cannot be null")
    private String time;
    @NotBlank(message = "Location cannot be empty")
    private String location;

    private int temperature;

    @OneToOne
    @JoinColumn(name = "addedBy")
    private User addedBy;

//    @OneToOne
//    @JoinColumn(name = "group_id", referencedColumnName = "id")
//    private Group group;
    @OneToMany(mappedBy = "weekend", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "weekend", cascade = CascadeType.ALL)
    private List<Group> groups;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public int getTemperature() {
        return temperature;
    }

    public Long getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Group getGroup() {
//        return group;
//    }
//
//    public void setGroup(Group group) {
//        this.group = group;
//    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User user) {
        this.addedBy = user;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
