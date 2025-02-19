package com.entity;

import com.util.enums.AccountTypeEnum;
import com.util.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.HashSet;
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

    String email;

    String password;

    @Column(name = "refresh_token", columnDefinition = "MEDIUMTEXT")
    String refreshToken;

    String avatar;

    String fullname;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    AccountTypeEnum accountType;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    Instant dob;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    Boolean active;

    Boolean locked;

    @OneToOne(mappedBy = "user")
    OTPEntity otp;

    @OneToOne(mappedBy = "user")
    ExpertEntity expert;

    @OneToMany(mappedBy = "user")
    Set<OrderEntity> orders;

    @OneToMany(mappedBy = "user")
    Set<LikeEntity> likes;

    @OneToMany(mappedBy = "user")
    Set<BlogEntity> blogs;

    @OneToMany(mappedBy = "user")
    Set<CommentEntity> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RateEntity> rates = new HashSet<>();

    @OneToMany(mappedBy = "user")
    Set<QuizAttemptEntity> quizAttempts;

    @OneToMany(mappedBy = "user")
    Set<UserNotificationEntity> userNotifications;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        if (active == null) {
            active = true;
        }
        if (locked == null) {
            locked = false;
        }
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
