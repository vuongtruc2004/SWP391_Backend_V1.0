package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "otp")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OTPEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "otp_id")
    Long otpId;

    String code;

    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    Instant createdAt;

    Instant expiredAt;

    @PrePersist
    public void handlePrePersist() {
        createdAt = Instant.now();
        expiredAt = createdAt.plus(65, ChronoUnit.SECONDS);
    }
}