package com.hamza.studentcourse.repository;
import com.hamza.studentcourse.entity.Student;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;//JpaRepository automatically provides ready-made database operations.
import java.util.Optional;
import org.springframework.data.domain.Page;
// page is special container that holds a list of records.
import org.springframework.data.domain.Pageable;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAll( Pageable pageable);

    Page<Student> findByAge(Integer age, Pageable pageable);

    Optional<Student> findByEmail(String email);

}

