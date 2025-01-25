package nl.novi.gettogetherbackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import nl.novi.gettogetherbackend.models.User;

public class UserCreateDTO {

    @NotBlank(message = "Username cannot be empty")
    private String username;
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    private String role = "USER";


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
