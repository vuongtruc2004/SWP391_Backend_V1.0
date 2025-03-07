package com.dto.response;

import com.util.enums.MessageSenderEnum;
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
public class MessageResponse {

    String content;

    @Enumerated(EnumType.STRING)
    MessageSenderEnum role;
}
