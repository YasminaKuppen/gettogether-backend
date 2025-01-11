package nl.novi.gettogetherbackend.dtos;


import jakarta.validation.constraints.NotBlank;
import nl.novi.gettogetherbackend.models.User;

public class GroupCreateDTO {
    @NotBlank(message = "Type cannot be empty")
    private String type;
    private User creator;


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