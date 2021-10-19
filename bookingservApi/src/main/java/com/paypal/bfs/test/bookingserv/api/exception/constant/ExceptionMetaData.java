package com.paypal.bfs.test.bookingserv.api.exception.constant;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
public interface ExceptionMetaData {
    ErrorMetadata INPUT_SHOULD_NOT_BE_EMPTY = new ErrorMetadata("B001", "Empty request",
            "please provide proper request");
    ErrorMetadata FIRST_NAME_SHOULD_NOT_BE_NULL = new ErrorMetadata("B002", "First Name is required",
            "First Name is mandatory field");

    ErrorMetadata LAST_NAME_SHOULD_NOT_BE_NULL = new ErrorMetadata("B003", "Last Name is required",
            "Last Name is mandatory field");

    ErrorMetadata DOB_SHOULD_NOT_BE_NULL = new ErrorMetadata("B004", "Date of birth is required",
            "Date of birth is mandatory field");

    ErrorMetadata DOB_SHOULD__BE_IN_VALID_FORMAT = new ErrorMetadata("B005", "Invalid format",
            "Date of birth {0}, is not in valid format");

    ErrorMetadata CHECKIN_SHOULD_NOT_BE_NULL = new ErrorMetadata("B006", "Checkin Date is required",
            "Checkin Date is mandatory field");

    ErrorMetadata CHECKIN_SHOULD_BE_VALID_FORMAT = new ErrorMetadata("B007", "Invalid format",
            "Checkin Date {0} is not in valid format");

    ErrorMetadata CHECKOUT_SHOULD_NOT_BE_NULL = new ErrorMetadata("B008", "Checkout Date is required",
            "Checkout Date is mandatory field");

    ErrorMetadata CHECKOUT_SHOULD_BE_VALID_FORMAT  = new ErrorMetadata("B009", "Invalid format",
            "Checkout Date {0} is not in valid format");

    ErrorMetadata ADDRESS_LINE1_SHOULD_NOT_BE_NULL = new ErrorMetadata("B010", "address Line1 is required",
            "address Line1 is mandatory field");

    ErrorMetadata CITY_SHOULD_NOT_BE_NULL = new ErrorMetadata("B011", "City is required",
            "City is mandatory field");

    ErrorMetadata STATE_SHOULD_NOT_BE_NULL = new ErrorMetadata("B012", "State is required",
            "State is mandatory field");

    ErrorMetadata ZIP_CODE_SHOULD_NOT_BE_NULL = new ErrorMetadata("B013", "Zip Code is required",
            "Zip Code is mandatory field");

    ErrorMetadata TOTAL_PRICE_SHOULD_BE_NUMBER = new ErrorMetadata("B014", "Provide number value",
            "Total price should be Number");

    ErrorMetadata DEPOSITE_AMOUNT_SHOULD_BE_NUMBER = new ErrorMetadata("B015", "Provide number value",
            "Deposite Amount should be Number");
    ErrorMetadata IDEMPOTENCY_EXCEPTION = new ErrorMetadata("B016", "Booking already exist",
            "Try with some other booking details");
}
