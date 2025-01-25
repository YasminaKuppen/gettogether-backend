package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Weekend;

import java.util.List;

public class GroupResponseDTO {
    private Long id;
    private User user;
    private Weekend weekend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public Weekend getWeekend() {
        return weekend;
    }

    public void setWeekend(Weekend weekend) {
        this.weekend = weekend;
    }


}