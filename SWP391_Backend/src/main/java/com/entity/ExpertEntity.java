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
    UserEntity user;

    @OneToMany(mappedBy = "expert")
    Set<CourseEntity> courses;

    @ManyToMany
    @JoinTable(name = "expert_user", joinColumns = @JoinColumn(name = "expert_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<UserEntity> users; // cac nguoi dung follow
}
