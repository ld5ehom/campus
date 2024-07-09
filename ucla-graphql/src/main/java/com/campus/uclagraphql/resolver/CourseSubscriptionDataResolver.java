package com.campus.uclagraphql.resolver;

import com.campus.uclagraphql.model.Payment;
import com.campus.uclagraphql.model.CourseSubscription;
import com.campus.uclagraphql.model.User;
import com.campus.uclagraphql.service.EnrollmentService;
import com.campus.uclagraphql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CourseSubscriptionDataResolver {

    private final UserService userService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public CourseSubscriptionDataResolver(UserService userService, EnrollmentService enrollmentService) {
        this.userService = userService;
        this.enrollmentService = enrollmentService;
    }

    @SchemaMapping(typeName = "CourseSubscription", field = "user")
    public User getUser(CourseSubscription subscription) {
        return userService.findById(subscription.getUserId()).orElse(null);
    }

    @SchemaMapping(typeName = "CourseSubscription", field = "payment")
    public Payment getPayment(CourseSubscription subscription) {
        return enrollmentService.findPaymentById(subscription.getPaymentId());
    }
}
