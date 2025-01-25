package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @ManyToOne
    @JoinColumn(name = "lession_id")
    LessonEntity lesson;
}
