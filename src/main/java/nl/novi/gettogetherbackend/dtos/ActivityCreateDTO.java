package nl.novi.gettogetherbackend.dtos;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import nl.novi.gettogetherbackend.models.Weekend;

// Checked

public class ActivityCreateDTO {


    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private String location;

    @Min(0)
    private Float costs;

    private Weekend weekend;


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

}
