package com.hamza.studentcourse.controller;

import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hamza.studentcourse.dto.StudentRequestDTO;
import com.hamza.studentcourse.entity.Course;
import com.hamza.studentcourse.service.CourseService;
import jakarta.validation.Valid;
import com.hamza.studentcourse.dto.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.hamza.studentcourse.dto.StudentSummaryDTO;
import java.util.List;


import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentRequestDTO dto) {
        Student student = new Student();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        Student savedStudent = studentService.createStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid@RequestBody StudentRequestDTO dto) {
        Student student = new Student();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/courses")
    public ResponseEntity<Course> addCourseToStudent(
            @PathVariable Long id,
            @RequestBody Course course) {

        Course savedCourse = courseService.addCourseToStudent(id, course);

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }
    @GetMapping("{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudentId(@PathVariable Long id)  {
        List<Course> getCourse = courseService.getCoursesByStudentId(id);
        return ResponseEntity.ok(getCourse);
    }
    @GetMapping
    public ResponseEntity<PagedResponse<Student>> getStudents(
            @RequestParam(required = false) Integer age,
            //@RequestParam = “take this value from the query string (the part after ? in the URL)”
            @PageableDefault(size = 5) Pageable pageable) {

        Page<Student> page = studentService.getStudents(age, pageable);

        PagedResponse<Student> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );

        return ResponseEntity.ok(response);
    }
    // Find by email
    @GetMapping("/email")
    public Student getByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }

    // JPQL - older than
    @GetMapping("/older-than")
    public List<Student> getOlderThan(@RequestParam Integer age) {
        return studentService.getStudentsOlderThan(age);
    }

    // Native - younger than
    @GetMapping("/younger-than")
    public List<Student> getYoungerThan(@RequestParam Integer age) {
        return studentService.getStudentsYoungerThan(age);
    }

    // DTO projection
    @GetMapping("/summary")
    public List<StudentSummaryDTO> getSummaries() {
        return studentService.getStudentSummaries();
    }

    // Pagination + Sorting
    @GetMapping
    public Page<Student> getAll(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }
}