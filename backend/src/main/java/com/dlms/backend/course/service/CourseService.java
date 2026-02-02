package com.dlms.backend.course.service;

import com.dlms.backend.course.dto.CourseCreateRequest;
import com.dlms.backend.course.dto.CourseResponse;
import com.dlms.backend.course.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(CourseCreateRequest request);

    List<CourseResponse> getAllCourses();

    Optional<CourseResponse> getCourseById(String courseId);

    boolean deleteCourse(String courseId);

    Course updateCourse(String courseId, CourseCreateRequest request);
}
