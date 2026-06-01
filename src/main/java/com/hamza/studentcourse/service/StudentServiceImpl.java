package com.hamza.studentcourse.service;

import com.hamza.studentcourse.dto.StudentSummaryDTO;
import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.entity.StudentStatus;
import com.hamza.studentcourse.repository.StudentRepository;
import com.hamza.studentcourse.specification.StudentSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.hamza.studentcourse.exception.StudentNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

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
    @PreAuthorize("hasAuthority('STUDENT_CREATE')")
    public Student createStudent(Student student) {

        Student saved = studentRepository.save(student);

        // simulate error
        if (saved.getAge() < 18) {
            throw new RuntimeException("Invalid age");
        }

        return saved;
    }

    @Override
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    public List<Student> filterStudents(Integer age, StudentStatus status, String name) {

        Specification<Student> spec = Specification
                .where(StudentSpecification.hasAge(age))
                .and(StudentSpecification.hasStatus(status))
                .and(StudentSpecification.hasName(name));

        return studentRepository.findAll(spec);
    }

    @Override
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    public List<Student> getAllStudentsWithCourses() {
        return studentRepository.findAllWithCourses();
    }

    @Override
    @Transactional
    public void saveAllStudents(List<Student> students) {
        studentRepository.saveAll(students);
    }

    @Override
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    public Page<Student> getStudents(Integer age, Pageable pageable) {
        if (age != null) {
            return studentRepository.findByAge(age, pageable);
        }
        return studentRepository.findAll(pageable);
    }

    @Override
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    @PreAuthorize(
            "hasRole('ADMIN') or @securityService.isOwner(#id, authentication)"
    )
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAge(student.getAge());

        return studentRepository.save(existingStudent);
    }

    @PreAuthorize("hasAuthority('STUDENT_DELETE')")
    @Override
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }

    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @Override
    public List<Student> getStudentsOlderThan(Integer age) {
        return studentRepository.findStudentsOlderThan(age);
    }

    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @Override
    public List<Student> getStudentsYoungerThan(Integer age) {
        return studentRepository.findStudentsYoungerThan(age);
    }

    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @Override
    public List<StudentSummaryDTO> getStudentSummaries() {
        return studentRepository.getStudentSummaries();
    }

    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @Override
    public Page<Student> getAllStudents (Pageable pageable){
        return studentRepository.findAll(pageable);
    }
}