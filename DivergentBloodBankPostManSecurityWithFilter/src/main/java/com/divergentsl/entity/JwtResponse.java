package com.divergentsl.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class JwtResponse {

    private String token;
    private String refreshToken;
    private Date expiryDate;
    private String message;
    private String phone;
    private String authority;
}