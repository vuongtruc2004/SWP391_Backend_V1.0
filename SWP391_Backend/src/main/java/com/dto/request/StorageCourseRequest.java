package com.dto.request;

import com.util.enums.CartCourseStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class StorageCourseRequest {

    Long courseId;

    CartCourseStatus status;
}
