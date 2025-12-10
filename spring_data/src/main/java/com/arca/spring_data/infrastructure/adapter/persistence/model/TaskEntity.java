package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
