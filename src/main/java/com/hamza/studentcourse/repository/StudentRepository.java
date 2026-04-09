package com.hamza.studentcourse.repository;

import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.dto.StudentSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.courses")
    List<Student> findAllWithCourses();

    // 1️⃣ JPQL - Find by email
    Optional<Student> findByEmail(String email);

    Page<Student> findByAge(Integer age, Pageable pageable);
    // 2️⃣ JPQL - age greater than
    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> findStudentsOlderThan(@Param("age") Integer age);

    // 3️⃣ Native Query
    @Query(value = "SELECT * FROM students WHERE age < ?1", nativeQuery = true)
    List<Student> findStudentsYoungerThan(Integer age);

    // 4️⃣ Projection (DTO)
    @Query("SELECT new com.hamza.studentcourse.dto.StudentSummaryDTO(s.id, CONCAT(s.firstName, ' ', s.lastName)) FROM Student s")
    List<StudentSummaryDTO> getStudentSummaries();

    // 5️⃣ Pagination + Sorting
    Page<Student> findAll(Pageable pageable);
}