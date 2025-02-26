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

    Double price;

    List<String> subjects;

    String introduction;
}