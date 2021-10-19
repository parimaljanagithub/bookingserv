package com.paypal.bfs.test.bookingserv.api.service;

import com.paypal.bfs.test.bookingserv.api.dao.BookingDao;
import com.paypal.bfs.test.bookingserv.api.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponse;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponses;
import com.paypal.bfs.test.bookingserv.api.repository.BookingRepository;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 18/10/21
 */
public class BookingServiceTest {

    @Tested
    BookingService bookingService;
    @Injectable
    BookingRepository bookingRepository;
    @Injectable
    BookingDao dao;

    @Test
    public void testCreateBooking() throws ValidationException {
        BookingEntity be= new BookingEntity();
        Booking b = new Booking();
        Address address = new Address();
        b.setAddress(address);

        b.setFirstName("testFirstName");
        new Expectations() {{
            bookingRepository.save((BookingEntity)any);
            result = be;
            dao.checkIdempotency(b);
            result = Arrays.asList();
        }};
        BookingResponse booking = bookingService.createBooking(b);
        Assert.assertEquals(be.getFirstName(),booking.getFirstName());
    }

    @Test(expected = ValidationException.class)
    public void testCreateBookingWithNegativeScenario() throws ValidationException {
        BookingEntity be= new BookingEntity();
        Booking b = new Booking();
        Address address = new Address();
        b.setAddress(address);

        b.setFirstName("testFirstName");
        new Expectations() {{
            dao.checkIdempotency(b);
            result = Arrays.asList(new BookingEntity());
        }};
        BookingResponse booking = bookingService.createBooking(b);
    }

    @Test
    public void testFetchAllBookings() {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setFirstName("fjjdsf");
        new Expectations() {{
            bookingRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
            result = Arrays.asList(bookingEntity);
        }};

        final BookingResponses bookingResponses = bookingService.fetchAllBookings();
        Assert.assertEquals(bookingEntity.getFirstName(),bookingResponses.getBookings().get(0).getFirstName());
    }
}
