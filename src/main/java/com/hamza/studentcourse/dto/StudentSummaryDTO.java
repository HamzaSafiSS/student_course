package com.hamza.studentcourse.dto;

public class StudentSummaryDTO {

    private Long id;
    private String fullName;

    public StudentSummaryDTO(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}