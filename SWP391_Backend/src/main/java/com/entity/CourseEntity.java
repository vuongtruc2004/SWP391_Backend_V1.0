package com.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
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

    String courseName;

    String description;

    @Column(columnDefinition = "TEXT")
    String objectives;

    String thumbnail;

    Double price;

    Boolean accepted;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @ManyToMany
    @JoinTable(name = "course_subject",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    Set<SubjectEntity> subjects;

    @ManyToMany
    @JoinTable(name = "course_user",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<UserEntity> users;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    Set<LessonEntity> lessons;

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

    public List<String> getObjectiveList() {
        try {
            return new ObjectMapper().readValue(objectives, new TypeReference<>() {
            });
        } catch (IOException e) {
            return List.of();
        }
    }

    public void setObjectiveList(List<String> objectiveList) {
        try {
            this.objectives = new ObjectMapper().writeValueAsString(objectiveList);
        } catch (IOException e) {
            this.objectives = "[]";
        }
    }
}