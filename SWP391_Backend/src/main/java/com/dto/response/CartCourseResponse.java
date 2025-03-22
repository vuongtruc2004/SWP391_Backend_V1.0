package com.dto.response;

import com.util.enums.CartCourseStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCourseResponse {

    Long cartCourseId;

    CourseResponse course;

    @Enumerated(EnumType.STRING)
    CartCourseStatus status;
}
