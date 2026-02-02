package com.dlms.backend.course.dto;

import java.util.List;

public class CourseCreateRequest {
    private int courseId;
    private String title;
    private String description;
    private String instructor;
    private List<CourseModuleRequest> modules;

    public CourseCreateRequest() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<CourseModuleRequest> getModules() {
        return modules;
    }

    public void setModules(List<CourseModuleRequest> modules) {
        this.modules = modules;
    }
}
