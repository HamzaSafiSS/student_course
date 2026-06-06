package com.hamza.studentcourse.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn401WithoutToken() throws Exception {

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminShouldAccessStudents() throws Exception {

        // ROLE_ADMIN includes STUDENT_READ permission — both must be present
        mockMvc.perform(get("/api/students")
                        .with(user("admin")
                                .authorities(
                                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                                        new SimpleGrantedAuthority("STUDENT_READ"),
                                        new SimpleGrantedAuthority("STUDENT_CREATE"),
                                        new SimpleGrantedAuthority("STUDENT_UPDATE"),
                                        new SimpleGrantedAuthority("STUDENT_DELETE"),
                                        new SimpleGrantedAuthority("COURSE_READ"),
                                        new SimpleGrantedAuthority("COURSE_CREATE"),
                                        new SimpleGrantedAuthority("COURSE_UPDATE"),
                                        new SimpleGrantedAuthority("COURSE_DELETE")
                                )))
                .andExpect(status().isOk());
    }

    @Test
    void studentShouldNotDeleteStudent() throws Exception {

        // ROLE_STUDENT only has STUDENT_READ and COURSE_READ — no STUDENT_DELETE
        mockMvc.perform(delete("/api/students/1")
                        .with(user("student")
                                .authorities(
                                        new SimpleGrantedAuthority("ROLE_STUDENT"),
                                        new SimpleGrantedAuthority("STUDENT_READ"),
                                        new SimpleGrantedAuthority("COURSE_READ")
                                )))
                .andExpect(status().isForbidden());
    }
}