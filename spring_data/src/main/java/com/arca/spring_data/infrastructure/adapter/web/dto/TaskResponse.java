package com.arca.spring_data.infrastructure.adapter.web.dto;

import java.time.LocalDateTime;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime publishedDate;
    private Long courseId;
    private String courseName;

    public TaskResponse() {
    }

    public TaskResponse(Long id, String title, String description, LocalDateTime publishedDate, Long courseId, String courseName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
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
}

