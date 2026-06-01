package com.hamza.studentcourse.security;

import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final StudentRepository studentRepository;

    public SecurityService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean isOwner(Long studentId,
                           Authentication authentication) {

        Student student =
                studentRepository.findById(studentId)
                        .orElse(null);

        if (student == null) {
            return false;
        }

        return student.getEmail()
                .equals(authentication.getName());
    }
}