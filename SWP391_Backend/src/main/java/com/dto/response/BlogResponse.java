package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.enums.BlogStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant updatedAt;

    @Enumerated(EnumType.STRING)
    BlogStatusEnum blogStatus;

    Boolean pinned;

    Integer totalLikes;

    Integer totalComments;

    UserResponse user;

    Set<HashtagResponse> hashtags;
}
