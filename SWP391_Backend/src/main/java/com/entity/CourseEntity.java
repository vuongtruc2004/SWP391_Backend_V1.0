package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    Long courseId;

    String name;

    String description;

    String thumbnail;

    Double price;

    Boolean accepted;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @ManyToMany(mappedBy = "courses")
    List<SubjectEntity> subjects;

    @ManyToMany(mappedBy = "purchasedCourses")
    List<UserEntity> purchasers;

    @OneToMany(mappedBy = "course")
    List<LessonEntity> lessons;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    ExpertEntity expert;

    @OneToMany(mappedBy = "course")
    Set<LikeEntity> likes;

    @OneToMany(mappedBy = "course")
    Set<CommentEntity> comments;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.accepted = false;
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}