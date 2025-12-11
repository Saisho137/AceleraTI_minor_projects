package com.arca.spring_data.domain.usecase;

import com.arca.spring_data.domain.model.Course;

import java.util.List;

public interface GetTeacherCoursesUseCase {
    /**
     * Obtiene todos los cursos de un docente
     * @param teacherId ID del docente
     * @return Lista de cursos del docente
     */
    List<Course> execute(Long teacherId);
}

