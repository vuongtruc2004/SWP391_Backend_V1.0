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
@Table(name = "coupons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    Long couponId;

    @Column(name = "coupon_name")
    String couponName;

    @Column(name = "coupon_description")
    String couponDescription;

    @Column(name = "coupon_code")
    String couponCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    DiscountTypeEnum discountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_range")
    DiscountRangeEnum discountRange;

    @Column(name = "discount_percent")
    Double discountPercent;

    @Column(name = "discount_amount")
    Double discountAmount;

    @Column(name = "max_discount_amount")
    Double maxDiscountAmount;

    @Column(name = "min_order_value")
    Double minOrderValue;

    @Column(name = "max_uses")
    Long maxUses;

    @Column(name = "used_count")
    Long usedCount;

    @Column(name = "start_time")
    Instant startTime;

    @Column(name = "end_time")
    Instant endTime;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @ManyToMany
    @JoinTable(name = "coupon_course", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<CourseEntity> courses;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
