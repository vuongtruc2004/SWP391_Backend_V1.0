package com.dto.response;

import com.entity.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogResponse {

    Long blogId;

    String title;

    Instant createdAt;

    Instant updatedAt;

    String content;

    UserResponse creator;
}
