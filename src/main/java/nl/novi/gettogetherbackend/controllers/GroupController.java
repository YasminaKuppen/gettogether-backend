package nl.novi.gettogetherbackend.controllers;

import nl.novi.gettogetherbackend.dtos.UserResponseDTO;
import nl.novi.gettogetherbackend.mappers.UserMapper;
import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.repositories.GroupRepository;
import nl.novi.gettogetherbackend.repositories.UserRepository;
import nl.novi.gettogetherbackend.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// Checked

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupController(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    // Bad
    @GetMapping("/{groupId}/users")
    public ResponseEntity<Set<UserResponseDTO>> getUsersInGroup(@PathVariable Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        return optionalGroup.map(
                group -> {
                    Set<UserResponseDTO> userResponseDTOs = group.getUsers().stream()
                            .map(UserMapper::toResponseDTO)
                            .collect(Collectors.toSet());
                    return ResponseEntity.ok(userResponseDTOs);
                }).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Good
    @PostMapping("/{groupId}/addUser/{userId}")
    public ResponseEntity<String> addUserToGroup(
            @PathVariable Long groupId, @PathVariable Long userId
    ) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalGroup.isEmpty() || optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group or User not found.");
        }

        Group group = optionalGroup.get();
        User user = optionalUser.get();

        // Add user to group
        group.addUser(user);
        groupRepository.save(group);

        return ResponseEntity.ok("User added to group successfully.");
    }


}