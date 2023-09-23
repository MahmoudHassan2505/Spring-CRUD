package com.example.exceptions;

public class CustomException extends RuntimeException {

    public CustomException(CustomExceptionMessage customExceptionMessage){
        super(customExceptionMessage.name());
    }
}
