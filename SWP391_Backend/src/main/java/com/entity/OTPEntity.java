package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OTPEntity {
    static final int EXPIRATION_TIME = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    UserEntity user;

    @Builder.Default
    boolean active = false;
    LocalTime creatAt;
    LocalTime expiresAt;

    @PrePersist
    public void handlePrePersist() {
        this.creatAt = LocalTime.now();
        this.expiresAt = creatAt.plusMinutes(3).truncatedTo(ChronoUnit.MINUTES);
    }
}