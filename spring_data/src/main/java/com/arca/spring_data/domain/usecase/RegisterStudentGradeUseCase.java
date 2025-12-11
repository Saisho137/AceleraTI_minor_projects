package com.arca.spring_data.domain.usecase;

import com.arca.spring_data.domain.model.Enrollment;

public interface RegisterStudentGradeUseCase {
    /**
     * Registra o actualiza la nota de un estudiante en un curso
     * @param studentId ID del estudiante
     * @param courseId ID del curso
     * @param grade Nota del estudiante (0.0 a 5.0)
     * @return Enrollment con la nota registrada
     */
    Enrollment execute(Long studentId, Long courseId, Double grade);
}

