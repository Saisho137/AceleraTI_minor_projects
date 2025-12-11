package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enrollments", indexes = {
    @Index(name = "idx_enrollment_student", columnList = "student_id"),
    @Index(name = "idx_enrollment_course", columnList = "course_id")
})
public class EnrollmentEntity {
    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private UserEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    private Double grade;
}
