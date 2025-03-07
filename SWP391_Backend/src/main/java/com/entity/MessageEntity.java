package com.entity;

import com.util.enums.MessageSenderEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    Long messageId;

    @Column(columnDefinition = "LONGTEXT")
    String content;

    @Enumerated(EnumType.STRING)
    MessageSenderEnum role;

    @Column(name = "created_at")
    Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    ChatEntity chat;

    @PrePersist
    public void handlePrePersist() {
        if (role.equals(MessageSenderEnum.USER)) {
            this.createdAt = Instant.now();
        } else {
            this.createdAt = Instant.now().plusSeconds(1);
        }
    }
}
