package com.arca.spring_data.infrastructure.config;

import com.arca.spring_data.domain.port.CourseRepository;
import com.arca.spring_data.domain.port.EnrollmentRepository;
import com.arca.spring_data.domain.port.TaskRepository;
import com.arca.spring_data.domain.port.UserRepository;
import com.arca.spring_data.domain.usecase.GetCourseStudentsWithGradesUseCase;
import com.arca.spring_data.domain.usecase.GetCourseTasksUseCase;
import com.arca.spring_data.domain.usecase.GetTeacherCoursesUseCase;
import com.arca.spring_data.domain.usecase.RegisterStudentGradeUseCase;
import com.arca.spring_data.domain.usecase.impl.GetCourseStudentsWithGradesUseCaseImpl;
import com.arca.spring_data.domain.usecase.impl.GetCourseTasksUseCaseImpl;
import com.arca.spring_data.domain.usecase.impl.GetTeacherCoursesUseCaseImpl;
import com.arca.spring_data.domain.usecase.impl.RegisterStudentGradeUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public RegisterStudentGradeUseCase registerStudentGradeUseCase(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository,
            CourseRepository courseRepository) {
        return new RegisterStudentGradeUseCaseImpl(enrollmentRepository, userRepository, courseRepository);
    }

    @Bean
    public GetCourseStudentsWithGradesUseCase getCourseStudentsWithGradesUseCase(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository) {
        return new GetCourseStudentsWithGradesUseCaseImpl(enrollmentRepository, courseRepository);
    }

    @Bean
    public GetTeacherCoursesUseCase getTeacherCoursesUseCase(
            CourseRepository courseRepository,
            UserRepository userRepository) {
        return new GetTeacherCoursesUseCaseImpl(courseRepository, userRepository);
    }

    @Bean
    public GetCourseTasksUseCase getCourseTasksUseCase(
            TaskRepository taskRepository,
            CourseRepository courseRepository) {
        return new GetCourseTasksUseCaseImpl(taskRepository, courseRepository);
    }
}
