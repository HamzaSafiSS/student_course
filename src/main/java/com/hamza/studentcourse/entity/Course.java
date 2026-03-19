package com.hamza.studentcourse.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    // Direct access to related object course.getStudent().getName();
    // Each Course is linked to ONE Student
    // The link is stored using student_id in the courses table
    // And in Java, we access it as a Student object
    public Course() {}

    public Course(String name, String description, Student student) {
        this.name = name;
        this.description = description;
        this.student = student;
    }

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}