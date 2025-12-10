package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class EnrollmentEntity {
    @EmbeddedId
    EnrollmentId id;

    @ManyToOne
    @MapsId("studentId")
    UserEntity student;

    @ManyToOne
    @MapsId("courseId")
    CourseEntity course;

    Double grade;
}
