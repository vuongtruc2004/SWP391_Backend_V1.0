package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "blogs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    Long blogId;

    String title;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @Column(columnDefinition = "LONGTEXT")
    String content;

    @Column(name = "plain_content", columnDefinition = "LONGTEXT")
    String plainContent;

    String thumbnail;

    Boolean pinned;

    Boolean published;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    UserEntity user;

    @OneToMany(mappedBy = "blog")
    @JsonManagedReference
    Set<LikeEntity> likes;

    @OneToMany(mappedBy = "blog")
    @JsonManagedReference
    Set<CommentEntity> comments;

    @ManyToMany
    @JoinTable(name = "blog_tag",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonManagedReference
    Set<HashtagEntity> hashtags;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        if (pinned == null) pinned = false;
        if (published == null) {
            this.published = true;
        }
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}