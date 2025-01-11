package nl.novi.gettogetherbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public class WeekendCreateDTO {


    @NotNull(message = "Date cannot be null")
    private Date date;
    @NotNull(message = "Time cannot be null")
    private String time;
    @NotBlank(message = "Location cannot be empty")
    private String location;

    private int temperature;


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

}