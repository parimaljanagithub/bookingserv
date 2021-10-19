package com.paypal.bfs.test.bookingserv.api.exception.constant;

import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;

public class ErrorMetadata {
    final private String errorCode;
    final private String shortMessage;
     private String longMessage;


    ErrorMetadata(String errorCode, String shortMessage, String longMessage) {
        this.errorCode = errorCode;
        this.shortMessage = shortMessage;
        this.longMessage = longMessage;

    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getLongMessage() {
        return longMessage;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void formateMessge(String ... resourceIdentifier) {
        if(!StringUtils.isEmpty(longMessage) && resourceIdentifier !=null) {
            final MessageFormat format = new MessageFormat(longMessage);
            longMessage = format.format(resourceIdentifier);

        }
    }
}
