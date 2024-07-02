package com.campus.uclacourseservice.domain.courses.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import lombok.Data;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "professor_id", nullable = false)
    private Long professorId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<CourseSession> sessions;

    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<CourseRating> ratings;

    // CourseSessionService_addSessionToCourse
    public Course(Long courseId) {
        this.id = courseId;
    }
}
