package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @Column(name = "subject_name")
    String subjectName;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @ManyToMany(mappedBy = "subjects")
    Set<CourseEntity> courses;
}
