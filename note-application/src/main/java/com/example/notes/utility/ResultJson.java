package com.example.notes.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResultJson {
    private Integer success;
    private Object result;
    private String message;
    private HttpStatus errorCode;
}
