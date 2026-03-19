package com.hamza.studentcourse.service;

import com.hamza.studentcourse.entity.Course;
import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.repository.CourseRepository;
import com.hamza.studentcourse.repository.StudentRepository;
import com.hamza.studentcourse.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Course addCourseToStudent(Long studentId, Course course) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        course.setStudent(student);

        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCoursesByStudentId(Long studentId) {

        return courseRepository.findByStudentId(studentId);
    }
}