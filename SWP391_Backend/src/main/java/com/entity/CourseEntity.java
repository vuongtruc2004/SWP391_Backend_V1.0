package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @Column(name = "total_likes")
    Long totalLikes;

    Double rating;

    Boolean accepted;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity creator;

    @ManyToMany(mappedBy = "courses")
    List<SubjectEntity> subjects;

    @ManyToMany(mappedBy = "purchasedCourses")
    @JsonIgnore
    List<UserEntity> purchasers;

    @OneToMany(mappedBy = "course")
    List<LessonEntity> lessons;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    @JsonIgnore
    ExpertEntity expert;

    @PrePersist
    public void handlePrePersist (){
        this.createdAt = Instant.now();
        this.totalLikes = 0L;
        this.rating = null;
        this.accepted = false;
    }

    @PreUpdate
    public void handlePreUpdate(){
        this.updatedAt = Instant.now();
    }
}