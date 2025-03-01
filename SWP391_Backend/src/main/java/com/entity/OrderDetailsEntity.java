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
@Table(name = "order_details")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    Long orderDetailsId;
    
    Long courseId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;
}
