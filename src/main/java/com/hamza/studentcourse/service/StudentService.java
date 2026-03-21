package com.hamza.studentcourse.service;

import com.hamza.studentcourse.entity.Student;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudentService {

    Page<Student> getStudents(Integer age, Pageable pageable);

    Student createStudent(Student student); //ReturnType  MethodName(ParameterType parameter)

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
