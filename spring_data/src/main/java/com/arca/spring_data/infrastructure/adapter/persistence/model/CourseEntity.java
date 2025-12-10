package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String description;

    @ManyToOne
    UserEntity teacher;

    @OneToMany(mappedBy = "course")
    Set<EnrollmentEntity> enrollments;

    @OneToMany(mappedBy = "course")
    Set<TaskEntity> tasks;
}
