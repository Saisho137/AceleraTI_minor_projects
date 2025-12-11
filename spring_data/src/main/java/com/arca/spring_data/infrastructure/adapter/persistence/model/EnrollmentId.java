package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class EnrollmentId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;
}