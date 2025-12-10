package com.arca.spring_data.infrastructure.adapter.persistence.mapper;

import com.arca.spring_data.domain.model.Task;
import com.arca.spring_data.infrastructure.adapter.persistence.model.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final CourseMapper courseMapper;

    public TaskMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public Task toDomain(TaskEntity entity) {
        if (entity == null) {
            return null;
        }

        Task task = new Task();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setPublishedDate(entity.getPublishedAt());

        if (entity.getCourse() != null) {
            task.setCourse(courseMapper.toDomain(entity.getCourse()));
        }

        return task;
    }

    public TaskEntity toEntity(Task domain) {
        if (domain == null) {
            return null;
        }

        TaskEntity entity = new TaskEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setPublishedAt(domain.getPublishedDate());

        if (domain.getCourse() != null) {
            entity.setCourse(courseMapper.toEntity(domain.getCourse()));
        }

        return entity;
    }
}

