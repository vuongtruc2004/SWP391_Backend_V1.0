package com.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hashtags")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HashtagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    Long tagId;

    @Column(name = "tag_name")
    String tagName;

    @ManyToMany(mappedBy = "hashtags")
    Set<BlogEntity> blogs;
}
