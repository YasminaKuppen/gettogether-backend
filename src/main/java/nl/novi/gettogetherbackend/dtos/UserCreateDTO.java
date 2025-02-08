package nl.novi.gettogetherbackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserCreateDTO {

    @NotBlank(message = "Username cannot be empty")
    @Pattern(regexp = "^[^\\s]+$", message = "Username must not contain spaces")
    private String username;

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    private String role;


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

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
