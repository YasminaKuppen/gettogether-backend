package nl.novi.gettogetherbackend.dtos;

public class VoteCreateDTO {

    private Long userId;

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
