package com.paypal.bfs.test.bookingserv.api.exception;

import com.paypal.bfs.test.bookingserv.api.exception.constant.ErrorMetadata;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
public class ValidationException extends BookingException {


    public ValidationException(final String errorCode, final String shortMessage, final String longMessage,
            final Throwable cause) {
        super(errorCode, shortMessage, longMessage, cause);
    }

    //

    public static ValidationException create(final String errorCode, final String shortMessage,
            final String longMessage, final Throwable cause) {
        return new ValidationException(errorCode, shortMessage, longMessage, cause);
    }
    public static ValidationException create(final ErrorMetadata errorMetadata) {

        return new ValidationException( errorMetadata.getErrorCode(), errorMetadata.getShortMessage(),
                errorMetadata.getLongMessage(), null);
    }

    public static ValidationException create(final ErrorMetadata errorMetadata,
            String ... resourceIdentifier) {
        errorMetadata.formateMessge(resourceIdentifier);
        return new ValidationException( errorMetadata.getErrorCode(), errorMetadata.getShortMessage(),
                errorMetadata.getLongMessage(), null);
    }

    public static ValidationException create(final ErrorMetadata errorMetadata, final Throwable cause,
            String ... resourceIdentifier) {
        errorMetadata.formateMessge(resourceIdentifier);
        return new ValidationException( errorMetadata.getErrorCode(), errorMetadata.getShortMessage(),
                errorMetadata.getLongMessage(), cause);
    }




}
