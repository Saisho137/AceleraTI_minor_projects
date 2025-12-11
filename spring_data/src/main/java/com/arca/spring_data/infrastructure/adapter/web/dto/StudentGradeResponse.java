package com.arca.spring_data.infrastructure.adapter.web.dto;

public class StudentGradeResponse {

    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Double grade;

    public StudentGradeResponse() {
    }

    public StudentGradeResponse(Long studentId, String studentName, String studentEmail, Double grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
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

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}

