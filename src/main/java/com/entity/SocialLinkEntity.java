package com.entity;


import com.util.enums.SocialLinkEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "social_links")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_link_id")
    Long socialLinkId;

    @Column(name="socialLink")
    String socialLink;

    @Enumerated(EnumType.STRING)
    @Column(name="socialType")
    SocialLinkEnum socialType;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    ExpertEntity expert;

}
