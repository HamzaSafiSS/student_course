package com.hamza.studentcourse.repository;

import com.hamza.studentcourse.dto.StudentSummaryDTO;
import com.hamza.studentcourse.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses")
    List<Student> findAllWithCourses();

    Page<Student> findByAge(Integer age, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> findStudentsOlderThan(@Param("age") Integer age);

    @Query("SELECT s FROM Student s WHERE s.age < :age")
    List<Student> findStudentsYoungerThan(@Param("age") Integer age);

    Optional<Student> findByEmail(String email);

    // FIXED: Matches the 4-parameter constructor (id, firstName, lastName, email)
    @Query("SELECT new com.hamza.studentcourse.dto.StudentSummaryDTO(" +
            "s.id, s.firstName, s.lastName, s.email) " +
            "FROM Student s")
    List<StudentSummaryDTO> getStudentSummaries();
}