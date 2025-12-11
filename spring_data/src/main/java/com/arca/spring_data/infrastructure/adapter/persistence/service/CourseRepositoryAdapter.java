package com.arca.spring_data.infrastructure.adapter.persistence.service;

import com.arca.spring_data.domain.model.Course;
import com.arca.spring_data.domain.port.CourseRepository;
import com.arca.spring_data.infrastructure.adapter.persistence.mapper.CourseMapper;
import com.arca.spring_data.infrastructure.adapter.persistence.repository.CourseJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseJpaRepository jpaRepository;
    private final CourseMapper mapper;

    public CourseRepositoryAdapter(CourseJpaRepository jpaRepository, CourseMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(course)));
    }

    @Override
    public Optional<Course> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Course> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Course> findByTeacherId(Long teacherId) {
        return jpaRepository.findByTeacherId(teacherId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
