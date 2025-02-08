package nl.novi.gettogetherbackend.config;

import nl.novi.gettogetherbackend.security.JwtService;
import nl.novi.gettogetherbackend.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import nl.novi.gettogetherbackend.security.JwtRequestFilter;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {

        http
                .httpBasic(hp -> hp.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        //login:
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/validate-token").permitAll()
                        //users:
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}/weekends").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/username/{username}").permitAll()
                        //activity:
                        .requestMatchers(HttpMethod.POST, "/activity").permitAll()
                        .requestMatchers(HttpMethod.GET, "/activity/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/activity/weekend/{weekendId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/activity/{id}/image").permitAll()
                        .requestMatchers(HttpMethod.GET, "/activity/{id}/image").permitAll()
                        //weekends:
                        .requestMatchers(HttpMethod.GET, "/weekends/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/weekends").permitAll()
                        //vote:
                        .requestMatchers(HttpMethod.POST, "/votes").permitAll()
                        //groups:
                        .requestMatchers(HttpMethod.POST, "/groups/{groupId}/addUser/{userId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/groups/{groupId}/users").permitAll()
                        .anyRequest().denyAll()
                )
                .addFilterBefore(new JwtRequestFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        return builder.build();
    }
}