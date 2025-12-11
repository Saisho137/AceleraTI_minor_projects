package com.arca.spring_data.domain.port;

import com.arca.spring_data.domain.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    void delete(Enrollment enrollment);
}

