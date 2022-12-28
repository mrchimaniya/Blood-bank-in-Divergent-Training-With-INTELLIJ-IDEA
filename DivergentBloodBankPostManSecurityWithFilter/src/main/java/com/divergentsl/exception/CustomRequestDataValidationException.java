package com.divergentsl.exception;

public class CustomRequestDataValidationException extends RuntimeException{
    public CustomRequestDataValidationException(String message)
    {
        super(message);
    }
}
