package com.hamza.studentcourse.service;

import com.hamza.studentcourse.entity.Course;

import java.util.List;

public interface CourseService {

    Course addCourseToStudent(Long studentId, Course course);

    List<Course> getCoursesByStudentId(Long studentId);
}