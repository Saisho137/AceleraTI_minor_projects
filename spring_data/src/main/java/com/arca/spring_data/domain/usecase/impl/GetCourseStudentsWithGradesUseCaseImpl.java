package com.arca.spring_data.domain.usecase.impl;

import com.arca.spring_data.domain.exception.ResourceNotFoundException;
import com.arca.spring_data.domain.model.Enrollment;
import com.arca.spring_data.domain.port.CourseRepository;
import com.arca.spring_data.domain.port.EnrollmentRepository;
import com.arca.spring_data.domain.usecase.GetCourseStudentsWithGradesUseCase;

import java.util.List;

public class GetCourseStudentsWithGradesUseCaseImpl implements GetCourseStudentsWithGradesUseCase {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public GetCourseStudentsWithGradesUseCaseImpl(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Enrollment> execute(Long courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        return enrollmentRepository.findByCourseId(courseId);
    }
}

