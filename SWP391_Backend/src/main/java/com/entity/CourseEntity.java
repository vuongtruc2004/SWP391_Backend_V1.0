package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Column(name = "sale_price")
    Double salePrice;

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

    @ManyToMany(mappedBy = "courses")
    private Set<UserEntity> users = new HashSet<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    Set<LessonEntity> lessons;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    ExpertEntity expert;

    String introduction;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<LikeEntity> likes;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    Set<CommentEntity> comments;

    @Column(name = "original_price")
    Double originalPrice;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        if (this.introduction == null) {
            introduction = "https://youtu.be/TJV6VC83rwo?si=5Q5oR5G7GREnngxv";
        }
        if (accepted == null) {
            accepted = false;
        }
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
            List<String> trimObjectiveList=new ArrayList<>();
            for(String objective:objectiveList){
                trimObjectiveList.add(objective.trim());
            }
            this.objectives = new ObjectMapper().writeValueAsString(trimObjectiveList);
        } catch (IOException e) {
            this.objectives = "[]";
        }
    }
}
