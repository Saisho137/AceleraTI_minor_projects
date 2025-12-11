package com.arca.spring_data.infrastructure.adapter.persistence.repository;

import com.arca.spring_data.infrastructure.adapter.persistence.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByCourseId(Long courseId);

    List<TaskEntity> findByCourseIdOrderByPublishedAtDesc(Long courseId);

    List<TaskEntity> findByCourseIdAndPublishedAtAfter(Long courseId, LocalDateTime date);

    List<TaskEntity> findByTitleContainingIgnoreCase(String title);
}

