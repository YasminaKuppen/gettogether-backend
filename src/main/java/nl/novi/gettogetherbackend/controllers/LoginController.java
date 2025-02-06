package nl.novi.gettogetherbackend.controllers;

import com.fasterxml.jackson.core.type.WritableTypeId;
import nl.novi.gettogetherbackend.security.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import nl.novi.gettogetherbackend.dtos.UserLoginRequestDTO;
import nl.novi.gettogetherbackend.services.UserDetailsServiceImpl;
import nl.novi.gettogetherbackend.models.User;
import nl.novi.gettogetherbackend.dtos.UserLoginRequestDTO;

import java.util.Map;

@RestController
public class LoginController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public LoginController(AuthenticationManager man, JwtService service, UserDetailsServiceImpl userDetailsService) {
        this.authManager = man;
        this.jwtService = service;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUserName(), userLoginRequestDTO.getPassword());

        try {
            // Authenticate the user
            Authentication auth = authManager.authenticate(up);

            // Use the UserDetailsService to load the user's details (including the user ID)
            UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginRequestDTO.getUserName());

            // Cast to User to get the user ID
            User user = (User) userDetails; // Since User implements UserDetails
            Long userId = user.getId();  // Get the user ID from the User entity

            // Generate the JWT token
            String token = jwtService.generateToken(userDetails);

            // Respond with the token and user ID
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Token generated",
                            "token", token,
                            "userId", String.valueOf(userId)  // Include the user ID in the response
                    )
            );
        } catch (AuthenticationException ex) {
            // JSON-response bij fout
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }
    }

//origineel
//    @PostMapping("/login")
//    public ResponseEntity<String> signIn(@RequestBody UserLoginRequestDTO userLoginRequestDTO
//    ) {
//        UsernamePasswordAuthenticationToken up =
//                new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUserName(), userLoginRequestDTO.getPassword());
//
//        try {
//            Authentication auth = authManager.authenticate(up);
//
//            var ud = (UserDetails) auth.getPrincipal();
//            String token = jwtService.generateToken(ud);
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                    .body("Token generated");
//        } catch (AuthenticationException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
//        }
//    }
}