package com.dto.response;

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

    String content;

    String thumbnail;

    Instant createdAt;

    Instant updatedAt;

    UserResponse user;
}
