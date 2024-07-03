package com.campus.uclaenrollmentservice.domain.repository;

import com.campus.uclaenrollmentservice.domain.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByUserIdAndCourseId(long userId, long courseId);
    List<Enrollment> findAllByUserId(long userId);
}