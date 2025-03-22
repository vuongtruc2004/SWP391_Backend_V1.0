package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long orderId;

    @Column(name = "order_code")
    String orderCode;

    @Column(name = "payment_url", columnDefinition = "MEDIUMTEXT")
    String paymentUrl;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "expired_at")
    Instant expiredAt;

    @Column(name = "paid_at")
    Instant paidAt;

    @Column(name = "total_price")
    Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDetailsEntity> orderDetails;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    CouponEntity coupon;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }
}
