package com.arca.spring_data.infrastructure.adapter.persistence.service;

import com.arca.spring_data.domain.model.Enrollment;
import com.arca.spring_data.domain.port.EnrollmentRepository;
import com.arca.spring_data.infrastructure.adapter.persistence.mapper.EnrollmentMapper;
import com.arca.spring_data.infrastructure.adapter.persistence.model.EnrollmentId;
import com.arca.spring_data.infrastructure.adapter.persistence.repository.EnrollmentJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentRepositoryAdapter implements EnrollmentRepository {

    private final EnrollmentJpaRepository jpaRepository;
    private final EnrollmentMapper mapper;

    public EnrollmentRepositoryAdapter(EnrollmentJpaRepository jpaRepository, EnrollmentMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(enrollment)));
    }

    @Override
    public Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId) {
        EnrollmentId id = new EnrollmentId(studentId, courseId);
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Enrollment> findByStudentId(Long studentId) {
        return jpaRepository.findByStudentId(studentId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> findByCourseId(Long courseId) {
        return jpaRepository.findByCourseId(courseId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Enrollment enrollment) {
        jpaRepository.delete(mapper.toEntity(enrollment));
    }
}
