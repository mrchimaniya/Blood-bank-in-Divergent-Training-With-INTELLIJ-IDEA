package com.divergentsl.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseRender {
    int statusCode;
    String message;
    Object object;
}
