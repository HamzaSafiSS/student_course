package com.hamza.studentcourse.entity;

import java.util.Set;

public enum Role {

    ROLE_ADMIN(
            Set.of(
                    Permission.STUDENT_READ,
                    Permission.STUDENT_CREATE,
                    Permission.STUDENT_UPDATE,
                    Permission.STUDENT_DELETE,
                    Permission.COURSE_READ,
                    Permission.COURSE_CREATE,
                    Permission.COURSE_UPDATE,
                    Permission.COURSE_DELETE
            )
    ),

    ROLE_INSTRUCTOR(
            Set.of(
                    Permission.STUDENT_READ,
                    Permission.COURSE_READ,
                    Permission.COURSE_CREATE,
                    Permission.COURSE_UPDATE
            )
    ),

    ROLE_STUDENT(
            Set.of(
                    Permission.STUDENT_READ,
                    Permission.COURSE_READ
            )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}