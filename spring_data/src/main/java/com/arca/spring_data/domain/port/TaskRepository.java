package com.arca.spring_data.domain.port;

import com.arca.spring_data.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findByCourseId(Long courseId);
    List<Task> findByCourseIdOrderByPublishedDateDesc(Long courseId);
    void deleteById(Long id);
}

