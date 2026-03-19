package com.hamza.studentcourse.service;

import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.repository.StudentRepository;
import com.hamza.studentcourse.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
//A class uses implements to promise to provide concrete implementations for all methods defined in an interface.
//extends: Creates a subclass that inherits properties and methods from a parent class, promoting code reuse.
    private final StudentRepository studentRepository;

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    // Dependency Injection
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {


        // check duplicate email
        studentRepository.findByEmail(student.getEmail())
                .ifPresent(s -> {
                    log.info("Email already exist logger");
                });
        Integer s = student.getAge();
        if(s < 10) {
            log.info("age is done.");
        }
        else {
            log.info("Student successfully saved");

            return studentRepository.save(student);
        }
        return null;

    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAge(student.getAge());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }
}