package nl.novi.gettogetherbackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weekend_id", nullable = false)
    private Weekend weekend;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Group() {
    }

    public Group(Weekend weekend, User user) {
        this.weekend = weekend;
        this.user = user;
    }

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

    public Weekend getWeekend() {
        return weekend;
    }
    public void setWeekend(Weekend weekend) {
        this.weekend = weekend;
    }


}