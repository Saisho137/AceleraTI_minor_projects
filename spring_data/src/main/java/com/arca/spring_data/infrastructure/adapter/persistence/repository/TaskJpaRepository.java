package com.arca.spring_data.infrastructure.adapter.persistence.repository;

import com.arca.spring_data.infrastructure.adapter.persistence.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE t.course.id = :courseId")
    List<TaskEntity> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT t FROM TaskEntity t WHERE t.course.id = :courseId ORDER BY t.publishedAt DESC")
    List<TaskEntity> findByCourseIdOrderByPublishedAtDesc(@Param("courseId") Long courseId);

    @Query("SELECT t FROM TaskEntity t WHERE t.course.id = :courseId AND t.publishedAt > :date")
    List<TaskEntity> findByCourseIdAndPublishedAtAfter(
        @Param("courseId") Long courseId,
        @Param("date") LocalDateTime date
    );

    List<TaskEntity> findByTitleContainingIgnoreCase(String title);
}

