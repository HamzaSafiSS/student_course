package com.hamza.studentcourse.repository;
import com.hamza.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;//JpaRepository automatically provides ready-made database operations.
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}

