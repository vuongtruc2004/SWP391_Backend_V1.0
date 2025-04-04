package com.entity;

import com.util.enums.DiscountTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
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
public class CouponEntity implements Serializable {

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

    @Column(name = "discount_type")
    @Enumerated(EnumType.STRING)
    DiscountTypeEnum discountType;

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

    @OneToMany(mappedBy = "coupon")
    Set<OrderEntity> orders;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.usedCount = 0L;
    }
}
