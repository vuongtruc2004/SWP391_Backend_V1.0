package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marketings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MarketingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marketing_id")
    Long marketingId;

    @OneToOne(mappedBy = "marketing")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity creator;
}
