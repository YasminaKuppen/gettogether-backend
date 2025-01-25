package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.User;

public class VoteResponseDTO {

    private Long id;
    private User user;
    private Activity activity;
    private Integer votes;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Activity getActivity() {
        return activity;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public Integer getVotes() {
        return votes;
    }
    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
