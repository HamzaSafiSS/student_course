package com.hamza.studentcourse.service;

import com.hamza.studentcourse.dto.StudentSummaryDTO;
import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.repository.StudentRepository;
import com.hamza.studentcourse.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hamza.studentcourse.exception.StudentNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Student createStudent(Student student) {

        Student saved = studentRepository.save(student);

        // simulate error
        if (saved.getAge() < 18) {
            throw new RuntimeException("Invalid age");
        }

        return saved;
    }

    @Override
    public List<Student> getAllStudentsWithCourses() {
        return studentRepository.findAllWithCourses();
    }

    @Override
    @Transactional
    public void saveAllStudents(List<Student> students) {
        studentRepository.saveAll(students);
    }

    @Override
    public Page<Student> getStudents(Integer age, Pageable pageable) {
        if (age != null) {
            return studentRepository.findByAge(age, pageable);
        }
        return studentRepository.findAll(pageable);
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

    @Override
    public List<Student> getStudentsOlderThan(Integer age) {
        return studentRepository.findStudentsOlderThan(age);
    }

    @Override
    public List<Student> getStudentsYoungerThan(Integer age) {
        return studentRepository.findStudentsYoungerThan(age);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    @Override
    public List<StudentSummaryDTO> getStudentSummaries() {
        return studentRepository.getStudentSummaries();
    }
    @Override
    public Page<Student> getAllStudents (Pageable pageable){
        return studentRepository.findAll(pageable);
    }
}