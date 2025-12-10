package com.arca.spring_data.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private Long id;
    private String name;
    private String description;
    private User teacher;
    private Set<Enrollment> enrollments;
    private Set<Task> tasks;

    public Course() {
        this.enrollments = new HashSet<>();
        this.tasks = new HashSet<>();
    }

    public Course(Long id, String name, String description, User teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.enrollments = new HashSet<>();
        this.tasks = new HashSet<>();
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        this.enrollments.remove(enrollment);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }
}

