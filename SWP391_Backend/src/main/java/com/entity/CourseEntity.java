package com.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.enums.CourseStatusEnum;
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

    @Column(columnDefinition = "LONGTEXT")
    String courseName;

    @Column(columnDefinition = "LONGTEXT")
    String description;

    @Column(columnDefinition = "TEXT")
    String objectives;

    String thumbnail;

    String introduction;

    @Column(name = "course_status")
    @Enumerated(EnumType.STRING)
    CourseStatusEnum courseStatus;

    Double price;

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
    List<ChapterEntity> chapters;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    ExpertEntity expert;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RateEntity> rates;

    @OneToMany(mappedBy = "course")
    Set<OrderDetailsEntity> orderDetailsEntityList;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    CampaignEntity campaign;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
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