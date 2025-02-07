package nl.novi.gettogetherbackend.dtos;

import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.Weekend;

import java.util.List;
import java.util.Set;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String role;
//    private Set<Group> groups;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


}