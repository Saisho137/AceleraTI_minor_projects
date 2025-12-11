package com.arca.spring_data.domain.usecase;

import com.arca.spring_data.domain.model.Task;

import java.util.List;

public interface GetCourseTasksUseCase {
    /**
     * Obtiene todas las tareas de un curso ordenadas por fecha de publicación descendente
     * @param courseId ID del curso
     * @return Lista de tareas del curso
     */
    List<Task> execute(Long courseId);
}

