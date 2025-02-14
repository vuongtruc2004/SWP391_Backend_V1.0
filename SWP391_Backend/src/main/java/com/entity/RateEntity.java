package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rates")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    Long rateId;

    String content;

    Integer stars;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseEntity course;

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
