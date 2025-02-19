package com.entity;

import com.util.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

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

    @Column(name = "user_id")
    Long userId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    OrderStatusEnum orderStatus;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
        if (this.orderStatus == null) {
            this.orderStatus = OrderStatusEnum.PENDING;
        }
    }

    @PreUpdate
    public void handlePreUpdate() {
        this.updatedAt = Instant.now();
    }
}
