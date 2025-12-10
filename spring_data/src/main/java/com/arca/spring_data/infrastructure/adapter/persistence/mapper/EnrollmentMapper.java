package com.arca.spring_data.infrastructure.adapter.persistence.mapper;

import com.arca.spring_data.domain.model.Enrollment;
import com.arca.spring_data.infrastructure.adapter.persistence.model.EnrollmentEntity;
import com.arca.spring_data.infrastructure.adapter.persistence.model.EnrollmentId;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    public EnrollmentMapper(UserMapper userMapper, CourseMapper courseMapper) {
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    public Enrollment toDomain(EnrollmentEntity entity) {
        if (entity == null) {
            return null;
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(userMapper.toDomain(entity.getStudent()));
        enrollment.setCourse(courseMapper.toDomain(entity.getCourse()));
        enrollment.setGrade(entity.getGrade());

        return enrollment;
    }

    public EnrollmentEntity toEntity(Enrollment domain) {
        if (domain == null) {
            return null;
        }

        EnrollmentEntity entity = new EnrollmentEntity();

        if (domain.getStudent() != null && domain.getCourse() != null) {
            EnrollmentId id = new EnrollmentId(
                domain.getStudent().getId(),
                domain.getCourse().getId()
            );
            entity.setId(id);
        }

        entity.setStudent(userMapper.toEntity(domain.getStudent()));
        entity.setCourse(courseMapper.toEntity(domain.getCourse()));
        entity.setGrade(domain.getGrade());

        return entity;
    }
}

