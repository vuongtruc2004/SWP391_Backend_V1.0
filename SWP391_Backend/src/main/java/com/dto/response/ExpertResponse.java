package com.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertResponse {

    Long expertId;
    
    Integer totalCourses;

    Integer yearOfExperience;

    String achievement;

    UserResponse user;
}
