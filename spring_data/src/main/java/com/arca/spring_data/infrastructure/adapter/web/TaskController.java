package com.arca.spring_data.infrastructure.adapter.web;

import com.arca.spring_data.domain.model.Task;
import com.arca.spring_data.domain.usecase.GetCourseTasksUseCase;
import com.arca.spring_data.infrastructure.adapter.web.dto.TaskResponse;
import com.arca.spring_data.infrastructure.adapter.web.mapper.TaskDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final GetCourseTasksUseCase getCourseTasksUseCase;
    private final TaskDtoMapper mapper;

    public TaskController(
            GetCourseTasksUseCase getCourseTasksUseCase,
            TaskDtoMapper mapper) {
        this.getCourseTasksUseCase = getCourseTasksUseCase;
        this.mapper = mapper;
    }

    /**
     * Obtener tareas de un curso (anuncios)
     * GET /api/tasks/course/{courseId}
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TaskResponse>> getCourseTasks(@PathVariable Long courseId) {
        List<Task> tasks = getCourseTasksUseCase.execute(courseId);

        List<TaskResponse> response = tasks.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}

