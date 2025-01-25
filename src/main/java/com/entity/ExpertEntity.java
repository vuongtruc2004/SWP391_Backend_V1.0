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
    @Column(name="diplome")
    DiplomaEnum diploma;

    @Column(name="yearOfExperience")
    Integer yearOfExperience;

    @OneToOne(mappedBy = "expert")
    UserEntity user;

    @OneToMany(mappedBy = "expert")
    Set<CourseEntity> createdCourses;

    @OneToMany(mappedBy = "expert")
    Set<SocialLinkEntity> socialLinks;

}
