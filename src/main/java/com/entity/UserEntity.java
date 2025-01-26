package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;

    String username;

    String password;

    @Column(name = "refresh_token",columnDefinition = "MEDIUMTEXT")
    String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    AccountTypeEnum accountType;

    String avatar;

    String fullname;

    String email;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    Instant dob;

    @OneToMany(mappedBy = "user")
    Set<LikeEntity> likes;

    @OneToMany(mappedBy = "user")
    Set<CommentEntity> comments;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    Boolean active;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    OTPEntity otp;

    @ManyToMany
    @JsonIgnoreProperties(value = {"purchasers"})
    @JoinTable(name = "course_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<CourseEntity> purchasedCourses;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;

    @OneToOne
    @JoinColumn(name = "expert_id", referencedColumnName = "expert_id")
    ExpertEntity expert;

    @OneToOne
    @JoinColumn(name = "marketing_id", referencedColumnName = "marketing_id")
    MarketingEntity marketing;

    @OneToMany(mappedBy = "creator")
    Set<BlogEntity> createdBlogs;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.active = true;
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
