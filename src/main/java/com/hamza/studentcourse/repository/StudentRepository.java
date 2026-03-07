package com.hamza.studentcourse.repository;
import com.hamza.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student, Long> {
}

