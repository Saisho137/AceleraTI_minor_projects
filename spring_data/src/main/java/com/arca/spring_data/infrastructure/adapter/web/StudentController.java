package com.arca.spring_data.infrastructure.adapter.web;

import com.arca.spring_data.domain.model.Enrollment;
import com.arca.spring_data.domain.port.EnrollmentRepository;
import com.arca.spring_data.infrastructure.adapter.web.dto.EnrollmentResponse;
import com.arca.spring_data.infrastructure.adapter.web.mapper.EnrollmentDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDtoMapper mapper;

    public StudentController(EnrollmentRepository enrollmentRepository, EnrollmentDtoMapper mapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.mapper = mapper;
    }

    @GetMapping("/{studentId}/enrollments")
    public ResponseEntity<List<EnrollmentResponse>> getStudentEnrollments(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        List<EnrollmentResponse> response = enrollments.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
