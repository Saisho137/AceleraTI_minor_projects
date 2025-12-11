package com.arca.spring_data.infrastructure.adapter.web.mapper;

import com.arca.spring_data.domain.model.Course;
import com.arca.spring_data.infrastructure.adapter.web.dto.CourseResponse;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoMapper {

    public CourseResponse toResponse(Course course) {
        if (course == null) {
            return null;
        }

        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getTeacher() != null ? course.getTeacher().getId() : null,
                course.getTeacher() != null ? course.getTeacher().getName() : null
        );
    }
}

