package com.arca.spring_data.infrastructure.adapter.persistence.repository;

import com.arca.spring_data.infrastructure.adapter.persistence.model.CourseEntity;
import com.arca.spring_data.infrastructure.adapter.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {

    List<CourseEntity> findByTeacher(UserEntity teacher);

    List<CourseEntity> findByTeacherId(Long teacherId);

    List<CourseEntity> findByNameContainingIgnoreCase(String name);
}

