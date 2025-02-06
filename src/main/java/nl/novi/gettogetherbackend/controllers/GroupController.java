package nl.novi.gettogetherbackend.controllers;


import nl.novi.gettogetherbackend.dtos.UserResponseDTO;
import nl.novi.gettogetherbackend.mappers.UserMapper;
import nl.novi.gettogetherbackend.models.Group;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.repositories.GroupRepository;
import nl.novi.gettogetherbackend.repositories.UserRepository;
import nl.novi.gettogetherbackend.services.GroupService;
import nl.novi.gettogetherbackend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final GroupService groupService;
    private final UserRepository userRepository;

    public GroupController(GroupService groupService, GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupService = groupService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

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
    @GetMapping("/{groupId}/users")
    public ResponseEntity<Set<User>> getUsersInGroup(@PathVariable Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        return optionalGroup.map(
                group ->
                        ResponseEntity.ok(group.getUsers())).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }

}