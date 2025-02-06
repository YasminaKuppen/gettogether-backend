package nl.novi.gettogetherbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups") // Ensure table name does not conflict with SQL reserved keywords
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each group

    @ManyToOne
    @JoinColumn(name = "weekend_id", nullable = false)
    @JsonIgnore
    private Weekend weekend;

    @ManyToMany
    @JoinTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private Set<User> users = new HashSet<>(); // A group has multiple users

    public Group() {}

    public Group(Weekend weekend) {
        this.weekend = weekend;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Weekend getWeekend() {
        return weekend;
    }

    public void setWeekend(Weekend weekend) {
        this.weekend = weekend;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}
