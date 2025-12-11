package com.arca.spring_data.infrastructure.adapter.web.mapper;

import com.arca.spring_data.domain.model.Task;
import com.arca.spring_data.infrastructure.adapter.web.dto.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoMapper {

    public TaskResponse toResponse(Task task) {
        if (task == null) {
            return null;
        }

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPublishedDate(),
                task.getCourse() != null ? task.getCourse().getId() : null,
                task.getCourse() != null ? task.getCourse().getName() : null
        );
    }
}

