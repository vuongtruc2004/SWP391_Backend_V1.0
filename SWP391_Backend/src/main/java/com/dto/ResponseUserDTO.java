package com.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserDTO {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String avatar;
}
