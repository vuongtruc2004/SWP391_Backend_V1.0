package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import com.util.enums.JobEnum;
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

    @Column(name = "refresh_token")
    String refreshToken;

    String avatar;

    String fullname;

    String phone;

    String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    AccountTypeEnum accountType;

    @Enumerated(EnumType.STRING)
    JobEnum job;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    Instant dob;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    Boolean active;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    OTPEntity otp;

    @OneToOne(mappedBy = "user")
    ExpertEntity expert;

    @ManyToMany
    @JoinTable(name = "course_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnore
    List<CourseEntity> purchasedCourses;

    @OneToMany(mappedBy = "user")
    Set<LikeEntity> likes;

    @OneToMany(mappedBy = "user")
    Set<BlogEntity> blogs;

    @OneToMany(mappedBy = "user")
    Set<CommentEntity> comments;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnore
    RoleEntity role;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.active = false;
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
