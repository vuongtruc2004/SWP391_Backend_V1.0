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
@Table(name = "likes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    BlogEntity blog;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    CommentEntity comment;

    @Column(name = "created_at")
    Instant createdAt;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }
}
