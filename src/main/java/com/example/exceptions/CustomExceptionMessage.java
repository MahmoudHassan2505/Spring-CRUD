package com.example.exceptions;

public enum CustomExceptionMessage {
    NO_SUCH_ELEMENT_WITH_THIS_ID("No Such Element with this id"),
    Error("Error");

    private String message;

    CustomExceptionMessage(String message) {
        this.message=message;
    }

}
