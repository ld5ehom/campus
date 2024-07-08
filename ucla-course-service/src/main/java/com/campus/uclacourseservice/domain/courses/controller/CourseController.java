package com.campus.uclacourseservice.domain.courses.controller;

import com.campus.uclacourseservice.domain.courses.entity.Course;
import com.campus.uclacourseservice.domain.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course) {
        Course newCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(newCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(courseId, course);
        return ResponseEntity.ok(updatedCourse);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(
            @PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new RuntimeException("Cannot Find a course with ID : " + courseId ));
        return ResponseEntity.ok(course);
    }

    // GraphQL DataLoader Update
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(
            @RequestParam(required = false) List<Long> courseId) {
        List<Course> courses;
        if (courseId == null || courseId.isEmpty()) {
            courses = courseService.getAllCourses();
        } else {
            // courseID = 100 & course = 200
            courses = courseService.getCourseByIds(courseId);
        }

        return ResponseEntity.ok(courses);
    }
}