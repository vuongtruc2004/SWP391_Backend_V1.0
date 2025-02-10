package com.entity;

import com.util.enums.DiplomaEnum;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "diploma")
    DiplomaEnum diploma;

    @Column(name = "year_of_experience")
    Integer yearOfExperience;

    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @OneToMany(mappedBy = "expert")
    Set<CourseEntity> courses;

    @OneToMany(mappedBy = "expert")
    Set<QuizEntity> quizzes;
}
