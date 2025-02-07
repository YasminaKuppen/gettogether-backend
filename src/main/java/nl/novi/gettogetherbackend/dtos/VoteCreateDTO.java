package nl.novi.gettogetherbackend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;
import nl.novi.gettogetherbackend.models.Activity;
import org.springframework.beans.factory.annotation.Value;

// Checked

public class VoteCreateDTO {

    @NotNull(message = "User required")
    private Long userId;

    @NotNull(message = "Activity required")
    private Long  activityId;

    private int votes;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
    public Long getActivityId() {
        return activityId;
    }

    public void setVote(int votes) {
        this.votes = votes;
    }
    public int getVotes() {
        return votes;
    }

}
