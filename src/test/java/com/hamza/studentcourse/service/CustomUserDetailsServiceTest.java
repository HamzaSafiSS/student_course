package com.hamza.studentcourse.service;

import com.hamza.studentcourse.entity.Role;
import com.hamza.studentcourse.entity.User;
import com.hamza.studentcourse.repository.UserRepository;
import com.hamza.studentcourse.security.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomUserDetailsServiceTest {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldLoadUserByUsername() {

        User user = new User();

        user.setUsername("testuser");
        user.setEmail("testuser@test.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.ROLE_STUDENT));

        userRepository.save(user);

        UserDetails details =
                userDetailsService.loadUserByUsername("testuser");

        assertEquals("testuser", details.getUsername());
    }
}