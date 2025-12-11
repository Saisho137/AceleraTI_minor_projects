package com.arca.spring_data.infrastructure.adapter.web;

import com.arca.spring_data.domain.model.Course;
import com.arca.spring_data.domain.usecase.GetTeacherCoursesUseCase;
import com.arca.spring_data.infrastructure.adapter.web.dto.CourseResponse;
import com.arca.spring_data.infrastructure.adapter.web.mapper.CourseDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final GetTeacherCoursesUseCase getTeacherCoursesUseCase;
    private final CourseDtoMapper mapper;

    public CourseController(
            GetTeacherCoursesUseCase getTeacherCoursesUseCase,
            CourseDtoMapper mapper) {
        this.getTeacherCoursesUseCase = getTeacherCoursesUseCase;
        this.mapper = mapper;
    }

    /**
     * Obtener cursos de un docente
     * GET /api/courses/teacher/{teacherId}
     */
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseResponse>> getTeacherCourses(@PathVariable Long teacherId) {
        List<Course> courses = getTeacherCoursesUseCase.execute(teacherId);

        List<CourseResponse> response = courses.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}

