package com.hamza.studentcourse.service;

import com.hamza.studentcourse.entity.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student); //ReturnType  MethodName(ParameterType parameter)

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
