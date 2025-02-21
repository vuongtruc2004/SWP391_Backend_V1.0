package com.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.List;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {

    Long courseId;

    String courseName;

    String description;

    List<String> objectives;

    String thumbnail;

    Double salePrice;


    List<String> subjects;

//    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
//    Set<LessonEntity> lessons;

    String introduction;

    Double originalPrice;
}