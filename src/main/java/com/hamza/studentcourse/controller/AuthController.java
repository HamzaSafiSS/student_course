package com.hamza.studentcourse.controller;

import com.hamza.studentcourse.dto.*;
import com.hamza.studentcourse.entity.Role;
import com.hamza.studentcourse.entity.User;
import com.hamza.studentcourse.repository.UserRepository;
import com.hamza.studentcourse.security.JwtService;
import com.hamza.studentcourse.security.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.swagger.v3.oas.annotations.Operation;

import java.util.Set;

@Tag(name = "Authentication",
        description = "Endpoints for user login, registration, and token management")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            CustomUserDetailsService userDetailsService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(
            summary = "Login user",
            description = "Returns access token and refresh token"
    )
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails user =
                userDetailsService.loadUserByUsername(request.getUsername());

        String accessToken =
                jwtService.generateAccessToken(user);

        String refreshToken =
                jwtService.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }


    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRoles(Set.of(Role.ROLE_STUDENT));

        userRepository.save(user);

        return "User registered successfully";
    }
}