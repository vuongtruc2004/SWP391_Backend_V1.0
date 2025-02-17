package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "likes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonManagedReference
    BlogEntity blog;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonManagedReference
    CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @JsonManagedReference
    CommentEntity comment;

    @Column(name = "created_at")
    Instant createdAt;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }
}
