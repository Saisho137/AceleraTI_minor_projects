package com.arca.spring_data.infrastructure.adapter.persistence.service;

import com.arca.spring_data.domain.model.Task;
import com.arca.spring_data.domain.port.TaskRepository;
import com.arca.spring_data.infrastructure.adapter.persistence.mapper.TaskMapper;
import com.arca.spring_data.infrastructure.adapter.persistence.repository.TaskJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskRepositoryAdapter implements TaskRepository {

    private final TaskJpaRepository jpaRepository;
    private final TaskMapper mapper;

    public TaskRepositoryAdapter(TaskJpaRepository jpaRepository, TaskMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Task save(Task task) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(task)));
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Task> findByCourseId(Long courseId) {
        return jpaRepository.findByCourseId(courseId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Task> findByCourseIdOrderByPublishedDateDesc(Long courseId) {
        return jpaRepository.findByCourseIdOrderByPublishedAtDesc(courseId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
