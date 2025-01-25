package com.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageDetailsResponse<T> {

    Integer currentPage;

    Integer pageSize;

    Integer totalPages;

    Long totalElements;

    T content;
}
