package com.jsp.E_Commerce_project.util;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"timestamp","statusCode","message","path","data"})
public class ResponseStructure<T> {

    private String message;
    private int statusCode;
    private T data;
    private LocalDateTime timestamp;
    private String path;

    public ResponseStructure(){
        this.timestamp = LocalDateTime.now();
    }
}
