package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponse;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponses;
import com.paypal.bfs.test.bookingserv.api.repository.BookingRepository;
import com.paypal.bfs.test.bookingserv.api.service.BookingService;
import mockit.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 18/10/21
 */
public class BookingResourceImplTest {
    @Tested
    BookingResourceImpl bookingResourceImpl;
    @Injectable
    BookingService bookingService;

    @Test
    public void createBookingWithValidInput() throws ValidationException {
        Booking booking = prepareInput();
        new Expectations() {{
            bookingService.createBooking(booking);
            result = new BookingResponse();
        }};

        final ResponseEntity<BookingResponse> response = bookingResourceImpl.create(booking);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test(expected = ValidationException.class)
    public void createBookingWithNegativeScenario() throws ValidationException {
        Booking booking = prepareInput();
        booking.setDob(null);
        bookingResourceImpl.create(booking);
    }

    @Test
    public void getAllBooking() {
        BookingResponses br = new BookingResponses();
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setFirstName("testFirstName");
        br.setBookings(Arrays.asList(bookingResponse));
        new Expectations() {{
            bookingService.fetchAllBookings();
            result = br;
        }};

        ResponseEntity<BookingResponses> allBookings = bookingResourceImpl.getAllBookings();
        Assert.assertEquals(bookingResponse.getFirstName(), allBookings.getBody().getBookings().get(0).getFirstName());
    }

    @Test(expected = SQLException.class)
    public void getAllBookWithNegativeScenario() {
        new Expectations() {{
            bookingService.fetchAllBookings();
            result = new SQLException();
        }};
        bookingResourceImpl.getAllBookings();
    }

    private Booking prepareInput() {
        Booking booking = new Booking();
        Address address = new Address();
        booking.setFirstName("fname");
        booking.setLastName("lname");
        booking.setDob("2019-11-23");
        booking.setCheckinDatetime("2019-11-23T23:03:11Z");
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
