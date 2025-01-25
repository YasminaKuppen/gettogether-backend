package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;

import java.util.List;

public class ActivityResponseDTO {


    private String title;
    private String description;
    private User addedBy;
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

}
