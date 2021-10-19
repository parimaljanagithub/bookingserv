package com.paypal.bfs.test.bookingserv.api.validator;

import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;

import org.junit.Assert;
import org.junit.Test;

import static com.paypal.bfs.test.bookingserv.api.exception.constant.ExceptionMetaData.*;
import static com.paypal.bfs.test.bookingserv.api.validator.BookingValidator.validateCreateRequest;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 18/10/21
 */
public class BookingValidatorTest {


    @Test
    public void testWithAllValidInput() throws ValidationException {
        Booking booking = prepareInput();
        validateCreateRequest(booking);
    }
    @Test(expected = ValidationException.class)
    public void testValidationWithBlankInput() throws ValidationException {
        try {
            validateCreateRequest(null);
        } catch (ValidationException v){
            Assert.assertEquals(INPUT_SHOULD_NOT_BE_EMPTY.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }
    @Test(expected = ValidationException.class)
    public void testValidationWithoutFirstName() throws ValidationException {
        Booking booking = prepareInput();
        booking.setFirstName(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(FIRST_NAME_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithoutLastName() throws ValidationException {
        Booking booking = prepareInput();
        booking.setLastName(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(LAST_NAME_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }
    @Test(expected = ValidationException.class)
    public void testValidationWithoutDOB() throws ValidationException {
        Booking booking = prepareInput();
        booking.setDob(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(DOB_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithInvalidDOB() throws ValidationException {
        Booking booking = prepareInput();
        booking.setDob("23/06/07");
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(DOB_SHOULD__BE_IN_VALID_FORMAT.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithoutCheckinDate() throws ValidationException {
        Booking booking = prepareInput();
        booking.setCheckinDatetime(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(CHECKIN_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }
    @Test(expected = ValidationException.class)
    public void testValidationWithInvalidCheckinDate() throws ValidationException {
        Booking booking = prepareInput();
        booking.setDob("23/06/07 44");
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(CHECKIN_SHOULD_BE_VALID_FORMAT.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithoutCheckoutDate() throws ValidationException {
        Booking booking = prepareInput();
        booking.setCheckoutDatetime(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(CHECKOUT_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }
    @Test(expected = ValidationException.class)
    public void testValidationWithInvalidCheckoutDate() throws ValidationException {
        Booking booking = prepareInput();
        booking.setDob("23/06/07 44");
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(CHECKOUT_SHOULD_BE_VALID_FORMAT.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithInvalidTotalAmount() throws ValidationException {
        Booking booking = prepareInput();
        booking.setTotalPrice("344f");
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(TOTAL_PRICE_SHOULD_BE_NUMBER.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithInvalidDepositeAmount() throws ValidationException {
        Booking booking = prepareInput();
        booking.setDeposit("555g");
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(DEPOSITE_AMOUNT_SHOULD_BE_NUMBER.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }
    @Test(expected = ValidationException.class)
    public void testValidationWithEmptyAddressLine1() throws ValidationException {
        Booking booking = prepareInput();
        booking.getAddress().setLine1(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(ADDRESS_LINE1_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithEmptyAddressCity() throws ValidationException {
        Booking booking = prepareInput();
        booking.getAddress().setCity(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(CITY_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }
    @Test(expected = ValidationException.class)
    public void testValidationWithEmptyAddressState() throws ValidationException {
        Booking booking = prepareInput();
        booking.getAddress().setState(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(STATE_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }

    @Test(expected = ValidationException.class)
    public void testValidationWithEmptyAddressZipCode() throws ValidationException {
        Booking booking = prepareInput();
        booking.getAddress().setZipCode(null);
        try {
            validateCreateRequest(booking);
        } catch (ValidationException v){
            Assert.assertEquals(ZIP_CODE_SHOULD_NOT_BE_NULL.getShortMessage(), v.getErrorResponse().getShortMessage());
            throw v;
        }
    }


    private Booking prepareInput() {
        Booking booking = new Booking();
        Address address = new Address();
        booking.setFirstName("fname");
        booking.setLastName("lname");
        booking.setDob("2019-05-23");
        booking.setCheckinDatetime("2019-12-23T23:03:11Z");
        booking.setCheckoutDatetime("2019-11-23T23:03:11Z");
        booking.setTotalPrice("1000");
        booking.setDeposit("1000");
        address.setLine1("Line1");
        address.setLine2("");
        address.setCity("city");
        address.setState("state");
        address.setZipCode(721211);
        booking.setAddress(address);
        return booking;
    }
}
