package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.User;

public class GroupResponseDTO {
    private Long id;
    private String type;
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


}