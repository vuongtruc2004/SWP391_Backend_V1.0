package com.util;

import com.exception.custom.UserException;

public class CourseValidUtil {

    public static void validCourseTitleAndDescription(String title,String description){
        if(title.trim().split("\\s+").length>20){
            throw new UserException("Độ dài tên khoá học không được vượt quá 20 từ!");
        }
        if(description.trim().split("\\s+").length>1000){
            throw new UserException("Mô tả khoá học không được vượt quá 1000 từ!");
        }
    }
}
