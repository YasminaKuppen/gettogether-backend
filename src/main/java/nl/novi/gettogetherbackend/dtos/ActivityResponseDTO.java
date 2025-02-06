package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.models.Weekend;

import java.util.List;

public class ActivityResponseDTO {


    private String title;
    private String description;
    private String location;
    private Float costs;
    private User addedBy;
    private Weekend weekend;
    private Long id;
    private List<Vote> votes;

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

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Weekend getWeekend() {
        return weekend;
    }

    public void setWeekend(Weekend weekend) {
        this.weekend = weekend;
    }

}
