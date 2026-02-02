package com.dlms.backend.course.repository;

import com.dlms.backend.course.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findByCourseId(int courseId);

    void deleteByCourseId(int courseId);
}
