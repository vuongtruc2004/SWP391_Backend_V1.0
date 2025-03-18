package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeResponse {
    UserResponse user;
    @JsonIgnoreProperties({"user", "comment", "likes"})
    BlogResponse blog;
    @JsonIgnoreProperties({"likes"})
    CommentResponse comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;
}
