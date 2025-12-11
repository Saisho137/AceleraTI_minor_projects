package com.arca.spring_data.infrastructure.adapter.web.mapper;

import com.arca.spring_data.domain.model.Enrollment;
import com.arca.spring_data.infrastructure.adapter.web.dto.EnrollmentResponse;
import com.arca.spring_data.infrastructure.adapter.web.dto.StudentGradeResponse;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentDtoMapper {

    public EnrollmentResponse toResponse(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }

        return new EnrollmentResponse(
                enrollment.getStudent() != null ? enrollment.getStudent().getId() : null,
                enrollment.getStudent() != null ? enrollment.getStudent().getName() : null,
                enrollment.getCourse() != null ? enrollment.getCourse().getId() : null,
                enrollment.getCourse() != null ? enrollment.getCourse().getName() : null,
                enrollment.getGrade()
        );
    }

    public StudentGradeResponse toStudentGradeResponse(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }

        return new StudentGradeResponse(
                enrollment.getStudent() != null ? enrollment.getStudent().getId() : null,
                enrollment.getStudent() != null ? enrollment.getStudent().getName() : null,
                enrollment.getStudent() != null ? enrollment.getStudent().getEmail() : null,
                enrollment.getGrade()
        );
    }
}

