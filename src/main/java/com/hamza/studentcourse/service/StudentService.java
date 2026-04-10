package com.hamza.studentcourse.service;

import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.dto.StudentSummaryDTO;
import java.util.List;

import com.hamza.studentcourse.entity.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudentService {

    List<Student> filterStudents(Integer age, StudentStatus status, String name);

    Page<Student> getStudents(Integer age, Pageable pageable);

    Student getStudentByEmail(String email);

    Student createStudent(Student student); //ReturnType  MethodName(ParameterType parameter)

    List<Student> getStudentsOlderThan(Integer age);

    List<Student> getStudentsYoungerThan(Integer age);

    List<StudentSummaryDTO> getStudentSummaries();

    Page<Student> getAllStudents(Pageable pageable);

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    List<Student> getAllStudentsWithCourses();

    void saveAllStudents(List<Student> students);
}
