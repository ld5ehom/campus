package com.campus.uclagraphql.resolver;

import com.campus.uclagraphql.model.Course;
import com.campus.uclagraphql.model.CourseSession;
import com.campus.uclagraphql.model.User;
import com.campus.uclagraphql.service.CourseService;
import com.campus.uclagraphql.service.UserService;
import com.campus.uclagraphql.service.dummy.DummyCourseService;
import com.campus.uclagraphql.service.dummy.DummyUserService;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CourseDataResolver {
    private final CourseService courseService;
    private final UserService userService;

    public CourseDataResolver(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @SchemaMapping(typeName = "Course", field = "courseSessions")
    public List<CourseSession> getSessions(Course course) {
        return courseService.findAllSessionsByCourseId(course.getId());
    }

    @SchemaMapping(typeName = "Course", field = "instructor")
    public User getInstructor(Course course) {
        return userService.findById(course.getInstructorId()).orElseThrow();
    }
}