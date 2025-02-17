package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "experts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expert_id")
    Long expertId;

    String job;

    @Column(columnDefinition = "MEDIUMTEXT")
    String achievement;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @Column(name = "year_of_experience")
    Integer yearOfExperience;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    UserEntity user;

    @OneToMany(mappedBy = "expert")
    @JsonManagedReference
    Set<CourseEntity> courses;

    @OneToMany(mappedBy = "expert")
    @JsonManagedReference
    Set<QuizEntity> quizzes;
}
