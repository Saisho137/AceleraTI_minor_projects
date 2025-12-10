package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    CourseEntity course;

    String title;
    String description;
    LocalDateTime publishedAt;
}
