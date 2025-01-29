package com.entity;

import com.util.enums.RoleNameEnum;
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
    UserEntity user;

    @OneToMany(mappedBy = "blog")
    Set<LikeEntity> likes;

    @OneToMany(mappedBy = "blog")
    Set<CommentEntity> comments;

    @ManyToMany
    @JoinTable(name = "blog_tag",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    Set<HashtagEntity> hashtags;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.pinned = false;
        this.published = user.getRole().getRoleName() != RoleNameEnum.USER;
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}