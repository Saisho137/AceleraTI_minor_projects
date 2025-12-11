package com.arca.spring_data.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class RegisterGradeRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Grade is required")
    @DecimalMin(value = "0.0", message = "Grade must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Grade must be at most 5.0")
    private Double grade;

    public RegisterGradeRequest() {
    }

    public RegisterGradeRequest(Long studentId, Long courseId, Double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}

