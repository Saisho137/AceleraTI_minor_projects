package com.arca.spring_data.domain.usecase.impl;

import com.arca.spring_data.domain.exception.ResourceNotFoundException;
import com.arca.spring_data.domain.model.Task;
import com.arca.spring_data.domain.port.CourseRepository;
import com.arca.spring_data.domain.port.TaskRepository;
import com.arca.spring_data.domain.usecase.GetCourseTasksUseCase;

import java.util.List;

public class GetCourseTasksUseCaseImpl implements GetCourseTasksUseCase {

    private final TaskRepository taskRepository;
    private final CourseRepository courseRepository;

    public GetCourseTasksUseCaseImpl(
            TaskRepository taskRepository,
            CourseRepository courseRepository) {
        this.taskRepository = taskRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Task> execute(Long courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        return taskRepository.findByCourseIdOrderByPublishedDateDesc(courseId);
    }
}

