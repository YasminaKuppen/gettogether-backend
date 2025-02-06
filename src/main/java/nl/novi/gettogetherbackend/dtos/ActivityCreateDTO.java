package nl.novi.gettogetherbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.models.Weekend;

import java.util.List;


public class ActivityCreateDTO {

    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private String location;
    private Float costs;
    //    @NotBlank(message = "Added by cannot be empty")
    private User addedBy;
    //    private List<Votes> votes;
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

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public Weekend getWeekend() {
        return weekend;
    }

    public void setWeekend(Weekend weekend) {
        this.weekend = weekend;
    }
//
//    public List<Votes> getVotes() {
//        return votes;
//    }
//
//    public void setVotes(List<Votes> votes) {
//        this.votes = votes;
//    }

}
