package com.hamza.studentcourse.entity;
import jakarta.persistence.*;//used to define the mapping between Java objects and relational database tables

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private Integer age;
}
