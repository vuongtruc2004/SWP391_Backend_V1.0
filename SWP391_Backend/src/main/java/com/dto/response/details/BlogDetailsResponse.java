package com.dto.response.details;

import com.dto.response.BlogResponse;
import com.dto.response.CommentResponse;
import com.dto.response.LikeResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogDetailsResponse extends BlogResponse {
    @JsonIgnoreProperties("blog")
    Set<CommentResponse> comments;
    
    @JsonIgnoreProperties("blog")
    Set<LikeResponse> likes;
}
