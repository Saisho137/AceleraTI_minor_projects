package com.arca.spring_data.infrastructure.adapter.web;

import com.arca.spring_data.domain.model.Enrollment;
import com.arca.spring_data.domain.usecase.GetCourseStudentsWithGradesUseCase;
import com.arca.spring_data.domain.usecase.RegisterStudentGradeUseCase;
import com.arca.spring_data.infrastructure.adapter.web.dto.EnrollmentResponse;
import com.arca.spring_data.infrastructure.adapter.web.dto.RegisterGradeRequest;
import com.arca.spring_data.infrastructure.adapter.web.mapper.EnrollmentDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final RegisterStudentGradeUseCase registerStudentGradeUseCase;
    private final GetCourseStudentsWithGradesUseCase getCourseStudentsWithGradesUseCase;
    private final EnrollmentDtoMapper mapper;

    public EnrollmentController(
            RegisterStudentGradeUseCase registerStudentGradeUseCase,
            GetCourseStudentsWithGradesUseCase getCourseStudentsWithGradesUseCase,
            EnrollmentDtoMapper mapper) {
        this.registerStudentGradeUseCase = registerStudentGradeUseCase;
        this.getCourseStudentsWithGradesUseCase = getCourseStudentsWithGradesUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/register-grade")
    public ResponseEntity<EnrollmentResponse> registerGrade(@Valid @RequestBody RegisterGradeRequest request) {
        Enrollment enrollment = registerStudentGradeUseCase.execute(
                request.getStudentId(),
                request.getCourseId(),
                request.getGrade()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(enrollment));
    }

    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<EnrollmentResponse>> getCourseStudentsWithGrades(@PathVariable Long courseId) {
        List<Enrollment> enrollments = getCourseStudentsWithGradesUseCase.execute(courseId);

        List<EnrollmentResponse> response = enrollments.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}

