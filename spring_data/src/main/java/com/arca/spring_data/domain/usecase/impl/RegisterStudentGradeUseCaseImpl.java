package com.arca.spring_data.domain.usecase.impl;

import com.arca.spring_data.domain.enums.Role;
import com.arca.spring_data.domain.exception.BusinessRuleException;
import com.arca.spring_data.domain.exception.InvalidGradeException;
import com.arca.spring_data.domain.exception.ResourceNotFoundException;
import com.arca.spring_data.domain.model.Course;
import com.arca.spring_data.domain.model.Enrollment;
import com.arca.spring_data.domain.model.User;
import com.arca.spring_data.domain.port.CourseRepository;
import com.arca.spring_data.domain.port.EnrollmentRepository;
import com.arca.spring_data.domain.port.UserRepository;
import com.arca.spring_data.domain.usecase.RegisterStudentGradeUseCase;

public class RegisterStudentGradeUseCaseImpl implements RegisterStudentGradeUseCase {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public RegisterStudentGradeUseCaseImpl(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository,
            CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment execute(Long studentId, Long courseId, Double grade) {
        validateGrade(grade);

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));

        if (student.getRole() != Role.STUDENT) {
            throw new BusinessRuleException("User with id " + studentId + " is not a student");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        Enrollment enrollment = enrollmentRepository
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElse(new Enrollment());

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(grade);

        return enrollmentRepository.save(enrollment);
    }

    private void validateGrade(Double grade) {
        if (grade == null) {
            throw new InvalidGradeException("Grade cannot be null");
        }
        if (grade < 0.0 || grade > 5.0) {
            throw new InvalidGradeException(grade);
        }
    }
}
