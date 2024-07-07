package com.campus.uclagraphql.resolver;

import com.campus.uclagraphql.model.Course;
import com.campus.uclagraphql.model.CourseRating;
import com.campus.uclagraphql.model.User;
import com.campus.uclagraphql.service.CourseService;
import com.campus.uclagraphql.service.UserService;
import com.campus.uclagraphql.service.dummy.DummyCourseService;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CourseRatingDataResolver {

    private final CourseService courseService;
    private final UserService userService;

    public CourseRatingDataResolver(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @SchemaMapping(typeName = "CourseRating", field = "course")
    public Course getCourse(CourseRating courseRating) {
        return courseService.findCourseById(courseRating.getCourseId()).orElseThrow();
    }

    @SchemaMapping(typeName = "CourseRating", field = "user")
    public User getUser(CourseRating courseRating) {
        return userService.findById(courseRating.getUserId()).orElseThrow();
    }
}