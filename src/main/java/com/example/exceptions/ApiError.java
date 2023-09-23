package com.example.exceptions;

import java.util.Date;

public class ApiError {

    private Integer errorCode;
    private CustomExceptionMessage message;
    private Date date;


    public ApiError(Integer errorCode, CustomExceptionMessage message, Date date) {
        this.errorCode = errorCode;
        this.message = message;
        this.date = date;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public CustomExceptionMessage getMessage() {
        return message;
    }

    public void setMessage(CustomExceptionMessage message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
