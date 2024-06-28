package com.campus.uclacourseservice.domain.courses.repository;

import com.campus.uclacourseservice.domain.courses.entity.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// CourseSessionService_getAllSessionsByCourseId
@Repository
public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
    List<CourseSession> findByCourseId(Long courseId);
}