package com.hamza.studentcourse.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        // Register the user before each test (data.sql already seeds, but this ensures BCrypt-encoded password)
        String registerRequest = """
                {
                  "username":"hamza_auth_test",
                  "email":"hamza_auth_test@test.com",
                  "password":"123456"
                }
                """;

        mockMvc.perform(
                post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequest)
        );
    }

    @Test
    void loginShouldReturnToken() throws Exception {

        String request = """
                {
                  "username":"hamza_auth_test",
                  "password":"123456"
                }
                """;

        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }
}