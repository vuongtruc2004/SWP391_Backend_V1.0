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
@Table(name = "videos")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    Long videoId;

    String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @Column(name = "video_url")
    String videoUrl;

    Integer duration;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    ChapterEntity chapter;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
