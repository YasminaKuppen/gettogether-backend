package nl.novi.gettogetherbackend.controllers;

import jakarta.validation.Valid;
import nl.novi.gettogetherbackend.dtos.UserCreateDTO;
import nl.novi.gettogetherbackend.dtos.UserResponseDTO;
import nl.novi.gettogetherbackend.mappers.UserMapper;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponseDTO(savedUser));

    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            User updatedUser = userService.save(user);
            return ResponseEntity.ok(UserMapper.toResponseDTO(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role
    ) {

        return ResponseEntity.ok(UserMapper.toResponseDTOList(userService.getUsers(id, username, email, role)));
    }

    //aangepast:
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Zet de gebruiker om naar een response DTO
            UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(user);
            return ResponseEntity.ok(userResponseDTO);
        } else {
            // Als de gebruiker niet wordt gevonden, geef dan een 404 Not Found terug
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/username")
    public ResponseEntity<UserResponseDTO> getUserByName(@RequestBody String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Zet de gebruiker om naar een response DTO
            UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(user);
            return ResponseEntity.ok(userResponseDTO);
        } else {
            // Als de gebruiker niet wordt gevonden, geef dan een 404 Not Found terug
            return ResponseEntity.notFound().build();
        }
    }


}