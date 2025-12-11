package com.arca.spring_data.domain.model;

public class Enrollment {
    private Long id;
    private User student;
    private Course course;
    private Double grade;

    public Enrollment() {
    }

    public Enrollment(Long id, User student, Course course, Double grade) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
