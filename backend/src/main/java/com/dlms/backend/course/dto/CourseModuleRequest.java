package com.dlms.backend.course.dto;

import java.util.List;

public class CourseModuleRequest {
    private String title;
    private List<LessonRequest> lessons;

    public CourseModuleRequest() {
    }

    public CourseModuleRequest(String title, List<LessonRequest> lessons) {
        this.title = title;
        this.lessons = lessons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LessonRequest> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonRequest> lessons) {
        this.lessons = lessons;
    }
}
