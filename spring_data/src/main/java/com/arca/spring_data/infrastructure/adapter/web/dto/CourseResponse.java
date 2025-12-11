package com.arca.spring_data.infrastructure.adapter.web.dto;

public class CourseResponse {

    private Long id;
    private String name;
    private String description;
    private Long teacherId;
    private String teacherName;

    public CourseResponse() {
    }

    public CourseResponse(Long id, String name, String description, Long teacherId, String teacherName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

