package com.dlms.backend.course.service.impl;

import com.dlms.backend.course.dto.CourseCreateRequest;
import com.dlms.backend.course.dto.CourseResponse;
import com.dlms.backend.course.entity.Course;
import com.dlms.backend.course.mapper.CourseMapper;
import com.dlms.backend.course.repository.CourseRepository;
import com.dlms.backend.course.service.CourseService;
import org.springframework.stereotype.Service;
import com.dlms.backend.media.service.MediaService;
import com.dlms.backend.course.entity.CourseModule;
import com.dlms.backend.course.entity.Lesson;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MediaService mediaService;

    public CourseServiceImpl(CourseRepository courseRepository, MediaService mediaService) {
        this.courseRepository = courseRepository;
        this.mediaService = mediaService;
    }

    @Override
    public Course createCourse(CourseCreateRequest request) {
        Course course = CourseMapper.toEntity(request);
        Course savedCourse = courseRepository.save(course);
        syncMedia(savedCourse);
        return savedCourse;
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseResponse> getCourseById(String courseId) {
        return courseRepository.findById(courseId)
                .map(CourseMapper::toResponse);
    }

    @Override
    public boolean deleteCourse(String courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();

            // Delete associated media
            if (course.getModules() != null) {
                for (CourseModule module : course.getModules()) {
                    if (module.getLessons() != null) {
                        for (Lesson lesson : module.getLessons()) {
                            if (lesson.getMediaId() != null && !lesson.getMediaId().isEmpty()) {
                                try {
                                    mediaService.deleteMedia(lesson.getMediaId());
                                    System.out.println("Deleted media: " + lesson.getMediaId());
                                } catch (Exception e) {
                                    System.err.println(
                                            "Failed to delete media " + lesson.getMediaId() + ": " + e.getMessage());
                                    // Continue deleting course even if media delete fails? Yes, to avoid
                                    // inconsistency.
                                }
                            }
                        }
                    }
                }
            }

            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    @Override
    public Course updateCourse(String courseId, CourseCreateRequest request) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course existingCourse = courseOptional.get();
            // Map updates from request
            Course updatedData = CourseMapper.toEntity(request);

            // Preserve ID and Create Date
            updatedData.setId(existingCourse.getId());
            updatedData.setCourseId(existingCourse.getCourseId());
            updatedData.setCreatedAt(existingCourse.getCreatedAt());
            updatedData.setUpdatedAt(LocalDateTime.now());

            Course savedCourse = courseRepository.save(updatedData);
            syncMedia(savedCourse);
            return savedCourse;
        }
        return null;
    }

    private void syncMedia(Course course) {
        if (course.getModules() == null)
            return;

        String courseId = course.getId();
        if (courseId == null)
            courseId = String.valueOf(course.getCourseId());

        for (CourseModule module : course.getModules()) {
            if (module.getLessons() != null) {
                for (Lesson lesson : module.getLessons()) {
                    if (lesson.getMediaId() != null && !lesson.getMediaId().isEmpty()) {
                        try {
                            mediaService.assignCourse(lesson.getMediaId(), courseId);
                            System.out.println("Linked media " + lesson.getMediaId() + " to course " + courseId);
                        } catch (Exception e) {
                            System.err.println(
                                    "Failed to link media " + lesson.getMediaId() + " to course: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
