package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.UserCreateDTO;
import nl.novi.gettogetherbackend.dtos.UserResponseDTO;
import nl.novi.gettogetherbackend.dtos.WeekendResponseDTO;
import nl.novi.gettogetherbackend.mappers.UserMapper;
import nl.novi.gettogetherbackend.mappers.WeekendMapper;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import nl.novi.gettogetherbackend.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        User user = UserMapper.toEntity(userCreateDTO);
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponseDTO(savedUser));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            User updatedUser = userService.save(user);
            return ResponseEntity.ok(UserMapper.toResponseDTO(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email
    )

    {

        return ResponseEntity.ok(UserMapper.toResponseDTOList(userService.getUsers(username, email)));
    }

}