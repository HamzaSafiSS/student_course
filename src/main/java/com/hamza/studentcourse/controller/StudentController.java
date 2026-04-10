package com.hamza.studentcourse.controller;

import com.hamza.studentcourse.dto.ApiResponse;
import com.hamza.studentcourse.dto.PagedResponse;
import com.hamza.studentcourse.dto.StudentRequestDTO;
import com.hamza.studentcourse.dto.StudentSummaryDTO;
import com.hamza.studentcourse.entity.Course;
import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.entity.StudentStatus;
import com.hamza.studentcourse.service.CourseService;
import com.hamza.studentcourse.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/with-courses")
    public List<Student> getAllWithCourses() {
        return studentService.getAllStudentsWithCourses();
    }

    @GetMapping("/filter")
    public ApiResponse<List<Student>> filterStudents(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) StudentStatus status,
            @RequestParam(required = false) String name) {

        List<Student> students = studentService.filterStudents(age, status, name);
        return new ApiResponse<>(students, "Success", 200);
    }

    @PostMapping("/batch")
    public String batchInsert(@RequestBody List<Student> students) {
        studentService.saveAllStudents(students);
        return "Batch insert successful";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto) {
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
    public ResponseEntity<Course> addCourseToStudent(@PathVariable Long id, @RequestBody Course course) {
        Course savedCourse = courseService.addCourseToStudent(id, course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudentId(@PathVariable Long id) {
        List<Course> courses = courseService.getCoursesByStudentId(id);
        return ResponseEntity.ok(courses);
    }

    // Main endpoint with optional age filter + pagination
    @GetMapping
    public ResponseEntity<PagedResponse<Student>> getStudents(
            @RequestParam(required = false) Integer age,
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

    @GetMapping("/email")
    public Student getByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/older-than")
    public List<Student> getOlderThan(@RequestParam Integer age) {
        return studentService.getStudentsOlderThan(age);
    }

    @GetMapping("/younger-than")
    public List<Student> getYoungerThan(@RequestParam Integer age) {
        return studentService.getStudentsYoungerThan(age);
    }

    // DTO projection - Summary
    @GetMapping("/summary")
    public List<StudentSummaryDTO> getSummaries() {
        return studentService.getStudentSummaries();
    }

    // Removed duplicate @GetMapping
    // If you still need a simple getAll without age filter, use /all or remove this method
}