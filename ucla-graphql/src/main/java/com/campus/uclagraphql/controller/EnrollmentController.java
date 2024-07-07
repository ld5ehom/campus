package com.campus.uclagraphql.controller;

import com.campus.uclagraphql.model.CourseSubscription;
import com.campus.uclagraphql.model.Enrollment;
import com.campus.uclagraphql.model.Payment;
import com.campus.uclagraphql.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @QueryMapping
    public List<Enrollment> getUserEnrollments(@Argument Long userId) {
        return enrollmentService.getEnrollmentsByUserId(userId);
    }

    @QueryMapping
    public List<CourseSubscription> getUserCourseSubscriptions(@Argument Long userId) {
        return enrollmentService.getSubscriptionsByUserId(userId);
    }

    @MutationMapping
    public Payment purchaseCourse(@Argument Long userId, @Argument Long courseId, @Argument Float amount, @Argument String paymentMethod) {
        return enrollmentService.purchaseCourse(userId, courseId, amount, paymentMethod);
    }

    @MutationMapping
    public Payment purchaseSubscription(@Argument Long userId, @Argument Float amount, @Argument String paymentMethod) {
        return enrollmentService.purchaseSubscription(userId, amount, paymentMethod);
    }

    @QueryMapping
    public boolean checkCourseAccess(@Argument Long userId, @Argument Long courseId) {
        return enrollmentService.checkSubscriptionAccess(userId)
                || enrollmentService.checkCourseAccess(courseId, userId);
    }
}
