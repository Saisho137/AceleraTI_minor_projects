package com.arca.spring_data.domain.usecase.impl;

import com.arca.spring_data.domain.constants.ErrorMessages;
import com.arca.spring_data.domain.enums.Role;
import com.arca.spring_data.domain.exception.BusinessRuleException;
import com.arca.spring_data.domain.exception.ResourceNotFoundException;
import com.arca.spring_data.domain.model.Course;
import com.arca.spring_data.domain.model.User;
import com.arca.spring_data.domain.port.CourseRepository;
import com.arca.spring_data.domain.port.UserRepository;
import com.arca.spring_data.domain.usecase.GetTeacherCoursesUseCase;

import java.util.List;

public class GetTeacherCoursesUseCaseImpl implements GetTeacherCoursesUseCase {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public GetTeacherCoursesUseCaseImpl(
            CourseRepository courseRepository,
            UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Course> execute(Long teacherId) {
        // Validar que el docente existe
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", teacherId));

        // Validar que el usuario es un docente
        if (teacher.getRole() != Role.TEACHER) {
            throw new BusinessRuleException(String.format(ErrorMessages.USER_NOT_TEACHER, teacherId));
        }

        // Obtener cursos del docente
        return courseRepository.findByTeacherId(teacherId);
    }
}

