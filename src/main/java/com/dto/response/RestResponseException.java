package com.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestResponseException<T> {
    private int stausCode;

    private String errorMessage;

    private Object message;

    private T data;
}
