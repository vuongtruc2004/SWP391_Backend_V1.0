package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    Long subjectId;

    @Column(name = "subject_name", columnDefinition = "MEDIUMTEXT")
    String subjectName;

    @Column(columnDefinition = "LONGTEXT")
    String description;

    String thumbnail;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @ManyToMany(mappedBy = "subjects")
    Set<CourseEntity> courses;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
