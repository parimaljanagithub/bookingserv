package com.paypal.bfs.test.bookingserv.api.exception.handler;

import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
@ControllerAdvice
public class CustomExceptionHandler extends AbstractHandler{

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity customValidationHandler(ValidationException ve) {
       return toResponse(ve, HttpStatus.BAD_REQUEST);
    }
    // request body is missing
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity requestBodyNotFound(){
        return toResponse("2","Invalid request Body", "Request Body is not vaild",
                HttpStatus.BAD_REQUEST);
    }

    //Media type not supported
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity mediaTypeNotSupported(){
       return toResponse("3","Invalid Media Type", "Media Type Not Supported", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity requestMethodNotFound(){
        return toResponse("4","Invalid request method", "Request Method Not Supported",
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    // request body is missing
    @ExceptionHandler(Exception.class)
    public ResponseEntity OthersError(Exception e){
        e.printStackTrace();
        return toResponse("1","Unknown Error", "Internal Error happen",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
