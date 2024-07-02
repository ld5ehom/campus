package com.campus.uclacourseservice.domain.courses.repository;

import com.campus.uclacourseservice.domain.courses.entity.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// getAllRatingsByCourseId
@Repository
public interface CourseRatingRepository extends JpaRepository<CourseRating, Long> {
    List<CourseRating> findByCourseId(Long courseId);
}