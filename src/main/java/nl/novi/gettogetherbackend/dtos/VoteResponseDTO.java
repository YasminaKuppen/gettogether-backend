package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Vote;

// Checked

public class VoteResponseDTO {

    Long userId;
    Long activityId;
    Integer votes;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getVotes() {
        return votes;
    }
    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
