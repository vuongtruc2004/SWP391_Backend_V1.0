package com.entity;

import com.util.enums.DiscountRangeEnum;
import com.util.enums.DiscountTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaigns")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    Long campaignId;

    @Column(name = "campaign_name")
    String campaignName;

    @Column(name = "campaign_description")
    String campaignDescription;

    @Column(name = "discount_percentage")
    Double discountPercentage;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    DiscountTypeEnum discountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_range")
    DiscountRangeEnum discountRange;

    @Column(name = "start_time")
    Instant startTime;

    @Column(name = "end_time")
    Instant endTime;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "thumbnail_url")
    String thumbnailUrl;

    @OneToMany(mappedBy = "campaign")
    Set<CourseEntity> courses;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }
}
