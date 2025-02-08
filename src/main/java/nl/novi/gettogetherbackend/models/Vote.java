package nl.novi.gettogetherbackend.models;

import jakarta.persistence.*;


@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"activity_id", "user_id"})
})
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int votes;

    public Vote() {}

    public Vote(Activity activity, User user, int votes) {
        this.activity = activity;
        this.user = user;
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getVotes() {
        return votes;
    }
    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Long getUserId() {
        return user.getId();
    }

    public void setUserId(Long userId) {
        this.user.setId(userId);
    }

    public void setActivityId(Long activityId) {
        this.activity.setId(activityId);
    }

    public Long getActivityId() {
        return activity.getId();
    }
}
