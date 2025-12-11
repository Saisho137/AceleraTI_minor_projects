package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks", indexes = {
    @Index(name = "idx_task_course", columnList = "course_id"),
    @Index(name = "idx_task_published", columnList = "published_at")
})
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(name = "published_at", nullable = false)
    private LocalDateTime publishedAt;
}
