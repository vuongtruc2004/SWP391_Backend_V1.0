package com.entity;

import com.util.enums.OrderStatusEnum;
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
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long orderId;

    @Column(name = "order_code")
    String orderCode;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    OrderStatusEnum orderStatus;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @Column(name = "expired_at")
    Instant expiredAt;

    @Column(name = "total_amount")
    Integer totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    Set<OrderDetailsEntity> orderDetails;

    @PrePersist
    public void handlePrePersist() {
        if (this.orderStatus == null) {
            this.orderStatus = OrderStatusEnum.PENDING;
        }
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
