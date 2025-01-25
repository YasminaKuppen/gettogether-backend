package nl.novi.gettogetherbackend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.models.Activity;

public class VoteCreateDTO {

    @NotNull(message = "User required")
    private User user;

    @NotNull(message = "activity required")
    private Activity activity;

    @Min(1)
    @Max(1)
    private Integer votes;

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public Activity getActivity() {
        return activity;
    }
    public void setVote(Integer votes) {
        this.votes = votes;
    }
    public Integer getVotes() {
        return votes;
    }



}
