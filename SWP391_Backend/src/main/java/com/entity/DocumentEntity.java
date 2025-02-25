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
@Table(name = "documents")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    Long documentId;

    String title;

    @Column(columnDefinition = "LONGTEXT")
    String content;

    @Column(name = "plain_content", columnDefinition = "LONGTEXT")
    String plainContent;

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
