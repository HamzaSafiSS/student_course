package com.hamza.studentcourse.specification;

import com.hamza.studentcourse.entity.Student;
import com.hamza.studentcourse.entity.StudentStatus;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> hasAge(Integer age) {
        return (root, query, cb) ->
                age == null ? null : cb.equal(root.get("age"), age);
    }

    public static Specification<Student> hasStatus(StudentStatus status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Student> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null :
                        cb.like(cb.lower(root.get("firstName")), "%" + name.toLowerCase() + "%");
    }
}