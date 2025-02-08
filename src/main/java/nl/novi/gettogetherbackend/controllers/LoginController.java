package nl.novi.gettogetherbackend.controllers;

import nl.novi.gettogetherbackend.security.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import nl.novi.gettogetherbackend.dtos.UserLoginRequestDTO;
import nl.novi.gettogetherbackend.services.UserDetailsServiceImpl;
import nl.novi.gettogetherbackend.models.User;

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
            Authentication auth = authManager.authenticate(up);

            UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginRequestDTO.getUserName());

            User user = (User) userDetails;
            Long userId = user.getId();
            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Token generated",
                            "token", token,
                            "userId", String.valueOf(userId)
                    )
            );
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Map<String, String>> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Missing or invalid token"));
        }

        try {
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            User user = (User) userDetails;

            if (jwtService.validateToken(token)) {
                return ResponseEntity.ok(
                        Map.of(
                                "message", "Token is valid",
                                "token", token,
                                "userId", String.valueOf(user.getId())
                        )
                );
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Invalid token"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Token validation failed"));
        }
    }

}