package com.paypal.bfs.test.bookingserv.api.validator;

import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.api.exception.constant.ExceptionMetaData;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.utils.CustomDateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;


import static com.paypal.bfs.test.bookingserv.api.utils.CustomDateUtils.CHECKIN_DATE_FORMAT;
import static com.paypal.bfs.test.bookingserv.api.utils.CustomDateUtils.DOB_DATE_FORMAT;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
public class BookingValidator {


    public static void validateCreateRequest(Booking booking) throws ValidationException{

        //empty request
        if(booking == null) {
           throw ValidationException.create(ExceptionMetaData.INPUT_SHOULD_NOT_BE_EMPTY);
        }

        //validate fistname: should not be null
        if(StringUtils.isEmpty(booking.getFirstName())){
            throw ValidationException.create(ExceptionMetaData.FIRST_NAME_SHOULD_NOT_BE_NULL);
        }

        //validate lastname: should not be null
        if(StringUtils.isEmpty(booking.getLastName())){
            throw ValidationException.create(ExceptionMetaData.LAST_NAME_SHOULD_NOT_BE_NULL);
        }

        //Validate DOB: should not be null
        if(StringUtils.isEmpty(booking.getDob())){
            throw ValidationException.create(ExceptionMetaData.DOB_SHOULD_NOT_BE_NULL);
        }
        //Validate DOB: format validation
        if(!CustomDateUtils.isValidFormat(booking.getDob(), DOB_DATE_FORMAT)){
            throw ValidationException.create(ExceptionMetaData.DOB_SHOULD__BE_IN_VALID_FORMAT, booking.getDob());
        }

        //Validate checkin_datetime : should not be null
        if(StringUtils.isEmpty(booking.getCheckinDatetime())){
            throw ValidationException.create(ExceptionMetaData.CHECKIN_SHOULD_NOT_BE_NULL);
        }
        //Validate checkin_datetime : format validation
        if(!CustomDateUtils.isValidFormat(booking.getCheckinDatetime(), CHECKIN_DATE_FORMAT)){
            throw ValidationException.create(ExceptionMetaData.CHECKIN_SHOULD_BE_VALID_FORMAT, booking.getCheckinDatetime());
        }

        //Validate checkout_datetime : should not be null
        if(StringUtils.isEmpty(booking.getCheckoutDatetime())){
            throw ValidationException.create(ExceptionMetaData.CHECKOUT_SHOULD_NOT_BE_NULL);
        }
        //Validate checkout_datetime : format validation
        if(!CustomDateUtils.isValidFormat(booking.getCheckoutDatetime(), CHECKIN_DATE_FORMAT)){
            throw ValidationException.create(ExceptionMetaData.CHECKOUT_SHOULD_BE_VALID_FORMAT, booking.getCheckoutDatetime());
        }

        //Validate total_price : not a number
        if(!NumberUtils.isDigits(booking.getTotalPrice())){
            throw ValidationException.create(ExceptionMetaData.TOTAL_PRICE_SHOULD_BE_NUMBER);
        }

        //Validate deposit : not a number
        if(!NumberUtils.isDigits(booking.getDeposit())){
            throw ValidationException.create(ExceptionMetaData.DEPOSITE_AMOUNT_SHOULD_BE_NUMBER);
        }


      //address
        //Validate address line 1: should not be null
        if(StringUtils.isEmpty(booking.getAddress().getLine1())){
            throw ValidationException.create(ExceptionMetaData.ADDRESS_LINE1_SHOULD_NOT_BE_NULL);
        }
        //Validate address city: should not be null
        if(StringUtils.isEmpty(booking.getAddress().getCity())){
            throw ValidationException.create(ExceptionMetaData.CITY_SHOULD_NOT_BE_NULL);
        }
        //Validate address state: should not be null
        if(StringUtils.isEmpty(booking.getAddress().getState())){
            throw ValidationException.create(ExceptionMetaData.STATE_SHOULD_NOT_BE_NULL);
        }
        //Validate address sip code: should not be null
        if(booking.getAddress().getZipCode() == null){
            throw ValidationException.create(ExceptionMetaData.ZIP_CODE_SHOULD_NOT_BE_NULL);
        }

    }

    public static void  raisedIdempotencyException() throws ValidationException {
        throw ValidationException.create(ExceptionMetaData.IDEMPOTENCY_EXCEPTION);
    }

/*
    public static void main(String[] args) {
        String input ="44.3x";
        if(NumberUtils.isNumber(input)){
            System.out.println("jfsj");
        }
    }

 */

}
