package com.campus.uclaenrollmentservice.domain.repository;

import com.campus.uclaenrollmentservice.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(long userId);
}