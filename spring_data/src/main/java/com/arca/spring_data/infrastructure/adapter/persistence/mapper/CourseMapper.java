package com.arca.spring_data.infrastructure.adapter.persistence.mapper;

import com.arca.spring_data.domain.model.Course;
import com.arca.spring_data.infrastructure.adapter.persistence.model.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    private final UserMapper userMapper;

    public CourseMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Course toDomain(CourseEntity entity) {
        if (entity == null) {
            return null;
        }

        Course course = new Course();
        course.setId(entity.getId());
        course.setName(entity.getName());
        course.setDescription(entity.getDescription());

        if (entity.getTeacher() != null) {
            course.setTeacher(userMapper.toDomain(entity.getTeacher()));
        }

        return course;
    }

    public CourseEntity toEntity(Course domain) {
        if (domain == null) {
            return null;
        }

        CourseEntity entity = new CourseEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());

        if (domain.getTeacher() != null) {
            entity.setTeacher(userMapper.toEntity(domain.getTeacher()));
        }

        return entity;
    }
}

