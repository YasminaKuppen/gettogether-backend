package nl.novi.gettogetherbackend.dtos;

public class UserLoginRequestDTO {
    private String username;
    private String password;

    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}