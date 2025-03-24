package com.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequest {
    @NotBlank(message = "Tiêu đề không được để trống!")
    String title;
    @NotBlank(message = "Nội dung không được để trống!")
    String content;

    String plainContent;
    @NotBlank(message = "Cần có ít nhất một thumbnail!")
    String thumbnail;

    Boolean pinned;

    List<String> hashtags;
}
