package com.dlms.backend.course.entity;

import java.util.List;

public class CourseModule {
    private String title;
    private List<Lesson> lessons;

    public CourseModule() {
    }

    public CourseModule(String title, List<Lesson> lessons) {
        this.title = title;
        this.lessons = lessons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
