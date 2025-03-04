package com.entity;

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

    String question;

    String answer;

    @Column(name = "created_at")
    Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    ChatEntity chat;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }
}
