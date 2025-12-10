package com.arca.spring_data.infrastructure.adapter.persistence.repository;

import com.arca.spring_data.infrastructure.adapter.persistence.model.CourseEntity;
import com.arca.spring_data.infrastructure.adapter.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findByTeacher(UserEntity teacher);

    List<CourseEntity> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM CourseEntity c WHERE c.teacher.id = :teacherId")
    List<CourseEntity> findByTeacherId(@Param("teacherId") Long teacherId);
}

