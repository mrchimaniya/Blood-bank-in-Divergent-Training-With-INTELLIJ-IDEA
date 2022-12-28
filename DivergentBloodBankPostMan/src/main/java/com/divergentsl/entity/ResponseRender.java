package com.divergentsl.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseRender {
    String message;
    Object object;
}
