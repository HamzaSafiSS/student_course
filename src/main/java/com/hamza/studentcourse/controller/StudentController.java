package com.hamza.studentcourse.controller;

import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hamza.studentcourse.dto.StudentRequestDTO;
import com.hamza.studentcourse.entity.Course;
import com.hamza.studentcourse.service.CourseService;
import jakarta.validation.Valid;

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

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
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
}