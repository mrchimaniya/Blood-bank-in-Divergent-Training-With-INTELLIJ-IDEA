package com.divergentsl.exception;

public class CustomAuthenticationException extends RuntimeException{
    //contructor
    public CustomAuthenticationException(String message)
    {
        super(message);
    }
}
