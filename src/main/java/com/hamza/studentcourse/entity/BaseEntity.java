package com.hamza.studentcourse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
//This tells JPA that this class is not an entity by itself,
//but its fields and mappings will be inherited by actual entity classes
@EntityListeners(AuditingEntityListener.class)
//Registers Spring's auditing listener on this class
//This listener is responsible for automatically populating the @CreatedDate
public abstract class BaseEntity {

    @CreatedDate //Spring Data JPA will automatically set this field to the current date/time when the entity is first persisted
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    //Automatically updated to the current date/time every time the entity is modified and saved.
    private LocalDateTime updatedAt;

    @CreatedBy
    //: Automatically stores the username/principal (who created the record).
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    //Automatically stores who last modified the record
    private String updatedBy;
}
