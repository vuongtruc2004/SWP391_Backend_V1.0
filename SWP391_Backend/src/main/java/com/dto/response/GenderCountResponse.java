package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenderCountResponse {
    Long maleCount;
    Long femaleCount;
    Long unknownCount;
}
