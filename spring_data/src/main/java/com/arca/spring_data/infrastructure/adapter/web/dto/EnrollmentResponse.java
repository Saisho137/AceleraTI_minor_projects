package com.arca.spring_data.infrastructure.adapter.web.dto;

public class EnrollmentResponse {

    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private Double grade;

    public EnrollmentResponse() {
    }

    public EnrollmentResponse(Long studentId, String studentName, Long courseId, String courseName, Double grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}

