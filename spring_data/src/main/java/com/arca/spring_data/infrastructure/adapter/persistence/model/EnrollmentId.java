package com.arca.spring_data.infrastructure.adapter.persistence.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class EnrollmentId {
    Long studentId;
    Long courseId;
}