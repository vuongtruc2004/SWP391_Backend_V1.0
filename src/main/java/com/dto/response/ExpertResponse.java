package com.dto.response;

import com.util.enums.DiplomaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    DiplomaEnum diploma;

    Integer yearOfExperience;

    Integer totalCourses;
    
    UserResponse user;
}
