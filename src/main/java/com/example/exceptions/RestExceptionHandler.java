package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity handelElementNotFound(){
        ApiError apiError = new ApiError(400,CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID,new Date());
        return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
    }
}
