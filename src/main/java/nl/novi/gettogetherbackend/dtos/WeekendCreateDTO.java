package nl.novi.gettogetherbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nl.novi.gettogetherbackend.models.User;

import java.util.Date;


public class WeekendCreateDTO {

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Date cannot be null")
    private Date date;
    @NotNull(message = "Time cannot be null")
    private String time;
    @NotBlank(message = "Location cannot be empty")
    private String location;

    private int temperature;

    private User addedBy;

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