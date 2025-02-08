package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.ActivityResponseDTO;
import nl.novi.gettogetherbackend.dtos.UserCreateDTO;
import nl.novi.gettogetherbackend.dtos.UserResponseDTO;
import nl.novi.gettogetherbackend.dtos.WeekendResponseDTO;
import nl.novi.gettogetherbackend.mappers.ActivityMapper;
import nl.novi.gettogetherbackend.mappers.UserMapper;
import nl.novi.gettogetherbackend.mappers.WeekendMapper;
import nl.novi.gettogetherbackend.models.Activity;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.models.Weekend;
import nl.novi.gettogetherbackend.services.UserService;
import nl.novi.gettogetherbackend.services.WeekendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Checked

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final WeekendService weekendService;

    public UserController(UserService userService, WeekendService weekendService) {
        this.userService = userService;
        this.weekendService = weekendService;
    }


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        User user = UserMapper.toEntity(userCreateDTO);
        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponseDTO(savedUser));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(userDetails.getRole());
            User updatedUser = userService.save(user);
            return ResponseEntity.ok(UserMapper.toResponseDTO(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(user);
            return ResponseEntity.ok(userResponseDTO);
        } else {
            // Als de gebruiker niet wordt gevonden, geef dan een 404 Not Found terug
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(user);
            return ResponseEntity.ok(userResponseDTO);
        } else {
            // Als de gebruiker niet wordt gevonden, geef dan een 404 Not Found terug
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/weekends")
    public ResponseEntity<List<WeekendResponseDTO>> getUserWeekends(@PathVariable Long userId) {
        List<Weekend> weekends = weekendService.getUserWeekends(userId);

        // Map each Weekend entity to WeekendResponseDTO
        List<WeekendResponseDTO> responseDTOs = weekends.stream()
                .map(WeekendMapper::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }



}