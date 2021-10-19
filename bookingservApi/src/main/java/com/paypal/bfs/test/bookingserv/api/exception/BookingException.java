package com.paypal.bfs.test.bookingserv.api.exception;

import com.paypal.bfs.test.bookingserv.api.model.ErrorInfo;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
public class BookingException extends Exception {

    private final String errorCode;
    private final String shortMessage;
    private final String longMessage;


    public BookingException(final String errorCode, final String shortMessage, final String longMessage,
            final Throwable cause) {
        super(shortMessage, cause);
        this.errorCode = errorCode;
        this.shortMessage = shortMessage;
        this.longMessage = longMessage;

    }

    public ErrorInfo getErrorResponse() {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(errorCode);
        errorInfo.setLongMessage(longMessage);
        errorInfo.setShortMessage(shortMessage);
        return errorInfo;
    }
}
