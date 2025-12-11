package com.arca.spring_data.domain.usecase;

import com.arca.spring_data.domain.model.Enrollment;

import java.util.List;

public interface GetCourseStudentsWithGradesUseCase {
    /**
     * Obtiene todos los estudiantes matriculados en un curso con sus respectivas notas
     * @param courseId ID del curso
     * @return Lista de matriculaciones con estudiantes y notas
     */
    List<Enrollment> execute(Long courseId);
}

