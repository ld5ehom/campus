package com.campus.uclagraphql.service;

import com.campus.uclaenrollmentservice.domain.service.*;
import com.campus.uclagraphql.model.Enrollment;
import com.campus.uclagraphql.model.Payment;
import com.campus.uclagraphql.model.CourseSubscription;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @GrpcClient("ucla-enrollment-service")
    private EnrollmentServiceGrpc.EnrollmentServiceBlockingStub enrollmentStub;
    @GrpcClient("ucla-payment-service")
    private FakePaymentServiceGrpc.FakePaymentServiceBlockingStub paymentStub;

    public Payment purchaseCourse(long userId, long courseId, double amount, String paymentMethod) {
        Payment paymentResponse = createPayment(userId, "COURSE", amount, paymentMethod);
        registerCourse(userId, courseId, paymentResponse.getId());

        return paymentResponse;
    }

    public Payment purchaseSubscription(long userId, double amount, String paymentMethod) {
        Payment paymentResponse = createPayment(userId, "SUBSCRIPTION", amount, paymentMethod);
        manageSubscription(userId, System.currentTimeMillis(), System.currentTimeMillis() + 31536000000L, paymentResponse.getId());  // 1 year

        return paymentResponse;
    }

    private Payment createPayment(long userId, String type, double amount, String paymentMethod) {
        EnrollmentServiceOuterClass.PaymentRequest request = EnrollmentServiceOuterClass.PaymentRequest.newBuilder()
                .setUserId(userId)
                .setType(type)
                .setAmount(amount)
                .setPaymentMethod(paymentMethod)
                .build();
        return Payment.fromProto(paymentStub.createPayment(request));
    }

    private EnrollmentServiceOuterClass.CourseRegistrationResponse registerCourse(long userId, long courseId, long paymentId) {
        EnrollmentServiceOuterClass.CourseRegistrationRequest request = EnrollmentServiceOuterClass.CourseRegistrationRequest.newBuilder()
                .setUserId(userId)
                .setCourseId(courseId)
                .setPaymentId(paymentId)
                .build();
        return enrollmentStub.registerCourse(request);
    }

    private EnrollmentServiceOuterClass.SubscriptionResponse manageSubscription(long userId, long startDate, long endDate, long paymentId) {
        EnrollmentServiceOuterClass.SubscriptionRequest request = EnrollmentServiceOuterClass.SubscriptionRequest.newBuilder()
                .setUserId(userId)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setPaymentId(paymentId)
                .build();
        return enrollmentStub.manageSubscription(request);
    }

    public boolean checkCourseAccess(long courseId, long userId) {
        EnrollmentServiceOuterClass.CourseAccessRequest request = EnrollmentServiceOuterClass.CourseAccessRequest.newBuilder()
                .setCourseId(courseId)
                .setUserId(userId)
                .build();
        EnrollmentServiceOuterClass.CourseAccessResponse response = enrollmentStub.checkCourseAccess(request);
        return response.getHasAccess();
    }

    public boolean checkSubscriptionAccess(long userId) {
        EnrollmentServiceOuterClass.SubscriptionAccessRequest request = EnrollmentServiceOuterClass.SubscriptionAccessRequest.newBuilder()
                .setUserId(userId)
                .build();
        EnrollmentServiceOuterClass.SubscriptionAccessResponse response = enrollmentStub.checkSubscriptionAccess(request);
        return response.getHasAccess();
    }

    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        EnrollmentServiceOuterClass.UserEnrollmentsRequest request = EnrollmentServiceOuterClass.UserEnrollmentsRequest.newBuilder()
                .setUserId(userId)
                .build();
        EnrollmentServiceOuterClass.UserEnrollmentsResponse response = enrollmentStub.getUserEnrollments(request);
        return response.getEnrollmentsList().stream()
                .map(Enrollment::fromProto)
                .collect(Collectors.toList());
    }
    
    public List<CourseSubscription> getSubscriptionsByUserId(Long userId) {
        EnrollmentServiceOuterClass.UserSubscriptionsRequest request = EnrollmentServiceOuterClass.UserSubscriptionsRequest.newBuilder()
                .setUserId(userId)
                .build();
        EnrollmentServiceOuterClass.UserSubscriptionsResponse response = enrollmentStub.getUserCourseSubscriptions(request);
        return response.getSubscriptionsList().stream()
                .map(CourseSubscription::fromProto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "payment", key = "#paymentId")
    public Payment findPaymentById(Long paymentId) {
        EnrollmentServiceOuterClass.PaymentsByIdRequest request = EnrollmentServiceOuterClass.PaymentsByIdRequest.newBuilder()
                .setPaymentId(paymentId)
                .build();

        EnrollmentServiceOuterClass.PaymentsByIdResponse response = paymentStub.getPaymentsByPaymentId(request);
        return Payment.fromProto(response.getPayment());
    }
}