package com.entity;

import com.util.enums.CartCourseStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_course_id")
    Long cartCourseId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseEntity course;

    @Enumerated(EnumType.STRING)
    CartCourseStatus status;
}
