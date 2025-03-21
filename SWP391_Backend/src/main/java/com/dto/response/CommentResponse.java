package com.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {
    Long commentId;
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant updatedAt;
    @JsonIgnoreProperties("comments")
    UserResponse user;
    @JsonIgnoreProperties({"blog", "replies", "likes"})
    CommentResponse parentComment;
    @JsonIgnoreProperties({"blog", "parentComment"})
    Set<CommentResponse> replies;
    @JsonIgnoreProperties({"comment"})
    Set<LikeResponse> likes;
}
