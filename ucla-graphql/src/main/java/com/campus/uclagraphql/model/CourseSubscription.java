package com.campus.uclagraphql.model;

import com.campus.uclaenrollmentservice.domain.service.EnrollmentServiceOuterClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


// Course PlanSubscription
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSubscription implements Serializable {
    private Long id;
    private Long userId;
    private User user;
    private Long paymentId;
    private Payment payment;
    private String startDate;
    private String endDate;
    private String status; // Active, Expired

    public static CourseSubscription fromProto(EnrollmentServiceOuterClass.Subscription proto) {
        CourseSubscription subscription = new CourseSubscription();
        subscription.setId(proto.getSubscriptionId());
        subscription.setUserId(proto.getUserId());
        subscription.setPaymentId(proto.getPaymentId());
        subscription.setStatus(proto.getStatus());
        subscription.setStartDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(proto.getStartDate()), ZoneId.systemDefault()).toString());
        subscription.setEndDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(proto.getEndDate()), ZoneId.systemDefault()).toString());
        return subscription;
    }
}