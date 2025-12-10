package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class EnrollmentId {
    Long studentId;
    Long courseId;
}