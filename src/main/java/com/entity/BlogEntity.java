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

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity creator;

    @PrePersist
    public void handlePrePersist (){
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate(){
        this.updatedAt = Instant.now();
    }
}