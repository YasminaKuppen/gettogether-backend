package nl.novi.gettogetherbackend.services;

import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean delete(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;

        } else {
            return false;
        }
    }

    public List<User> getUsers(
            String username,
            String email
    ) {
        List<User> users;
        users = userRepository.findAll();
        return users;
    }
}