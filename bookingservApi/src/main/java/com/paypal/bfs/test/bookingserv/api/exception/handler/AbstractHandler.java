package com.paypal.bfs.test.bookingserv.api.exception.handler;

import com.paypal.bfs.test.bookingserv.api.exception.BookingException;
import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponse;
import com.paypal.bfs.test.bookingserv.api.model.ErrorInfo;
import com.paypal.bfs.test.bookingserv.api.model.Errors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
public abstract class AbstractHandler {
    private static final Logger logger = LogManager.getLogger();

    public abstract ResponseEntity customValidationHandler(ValidationException ve);

    ResponseEntity<Errors> toResponse(BookingException b, HttpStatus status) {
        Errors errors = new Errors();
        errors.setErrorset( Arrays.asList(b.getErrorResponse()));
        b.printStackTrace();
        logger.error(b);
        logger.error("Response status = "+ status.value());
        return ResponseEntity.status(status).body(errors);
    }

    ResponseEntity<Errors> toResponse(String errorCode, String shortMessage,String longMessage, HttpStatus status) {
        Errors errors = new Errors();
        ErrorInfo info = new ErrorInfo();
        info.setErrorCode(errorCode);
        info.setLongMessage(longMessage);
        info.setShortMessage(shortMessage);
        errors.setErrorset( Arrays.asList(info));
        logger.error(errors);
        logger.error("Response status = "+ status.value());
        return ResponseEntity.status(status).body(errors);
    }
}
