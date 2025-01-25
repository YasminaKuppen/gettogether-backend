package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.User;

import java.util.Date;


public class WeekendResponseDTO {

    private Long id;
    private String name;
    private Date date;
    private String time;
    private String location;
    private int temperature;
    private User addedBy;


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

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public int getTemperature() {
        return temperature;
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

    public User getAddedBy() {
        return addedBy;
    }
    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

}