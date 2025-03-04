package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chats")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    Long chatId;

    String title;

    @Column(name = "created_at")
    Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @OneToMany(mappedBy = "chat")
    List<MessageEntity> messages;

    @PrePersist
    public void handlePrePersist() {
        this.createdAt = Instant.now();
    }
}
