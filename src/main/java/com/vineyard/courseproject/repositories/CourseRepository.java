package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
    List<Course> findByDescription(String name);
    List<Course> findByTopicId(String topicId);
}
