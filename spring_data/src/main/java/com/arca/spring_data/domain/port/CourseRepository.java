package com.arca.spring_data.domain.port;

import com.arca.spring_data.domain.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);
    Optional<Course> findById(Long id);
    List<Course> findAll();
    List<Course> findByTeacherId(Long teacherId);
    void deleteById(Long id);
}

