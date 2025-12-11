package com.arca.spring_data.infrastructure.adapter.persistence.repository;

import com.arca.spring_data.infrastructure.adapter.persistence.model.EnrollmentEntity;
import com.arca.spring_data.infrastructure.adapter.persistence.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentEntity, EnrollmentId> {

    List<EnrollmentEntity> findByStudentId(Long studentId);

    List<EnrollmentEntity> findByCourseId(Long courseId);

    List<EnrollmentEntity> findByStudentIdAndGradeGreaterThanEqual(Long studentId, Double minGrade);

    @Query("SELECT AVG(e.grade) FROM EnrollmentEntity e WHERE e.student.id = :studentId")
    Double calculateAverageGradeByStudentId(@Param("studentId") Long studentId);
}

