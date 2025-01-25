package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "videos")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    Long videoId;

    @Column(name = "video_url")
    String videoUrl;

    @ManyToOne
    @JoinColumn(name = "lession_id")
    LessonEntity lesson;

}
