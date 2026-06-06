package com.hamza.studentcourse.service;

import com.hamza.studentcourse.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateToken() {

        UserDetails user = User.builder()
                .username("hamza")
                .password("password")
                .authorities("ROLE_ADMIN")
                .build();

        String token = jwtService.generateAccessToken(user);

        assertNotNull(token);
    }

    @Test
    void shouldExtractUsername() {

        UserDetails user = User.builder()
                .username("hamza")
                .password("password")
                .authorities("ROLE_ADMIN")
                .build();

        String token = jwtService.generateAccessToken(user);

        String username = jwtService.extractUsername(token);

        assertEquals("hamza", username);
    }

    @Test
    void shouldValidateToken() {

        UserDetails user = User.builder()
                .username("hamza")
                .password("password")
                .authorities("ROLE_ADMIN")
                .build();

        String token = jwtService.generateAccessToken(user);

        assertTrue(jwtService.isTokenValid(token, user));
    }
}