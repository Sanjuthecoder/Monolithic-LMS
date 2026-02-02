package com.dlms.backend.course.dto;

import java.util.List;

public class CourseModuleResponse {
    private String title;
    private List<LessonResponse> lessons;

    public CourseModuleResponse() {
    }

    public CourseModuleResponse(String title, List<LessonResponse> lessons) {
        this.title = title;
        this.lessons = lessons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LessonResponse> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonResponse> lessons) {
        this.lessons = lessons;
    }
}
