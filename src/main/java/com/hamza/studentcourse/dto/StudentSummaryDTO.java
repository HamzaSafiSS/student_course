package com.hamza.studentcourse.dto;

public class StudentSummaryDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // MANDATORY constructor that matches the JPQL "new" expression (4 parameters)
    public StudentSummaryDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // No-args constructor (good practice, especially if using in other places)
    public StudentSummaryDTO() {}

    // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // Optional: helper method for full name
    public String getFullName() {
        return (firstName != null ? firstName : "") +
                (lastName != null ? " " + lastName : "");
    }

    @Override
    public String toString() {
        return "StudentSummaryDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}