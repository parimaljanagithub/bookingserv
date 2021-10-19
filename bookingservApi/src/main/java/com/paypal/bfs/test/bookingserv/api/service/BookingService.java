package com.paypal.bfs.test.bookingserv.api.service;

import com.paypal.bfs.test.bookingserv.api.dao.BookingDao;
import com.paypal.bfs.test.bookingserv.api.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponse;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponses;
import com.paypal.bfs.test.bookingserv.api.repository.BookingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.paypal.bfs.test.bookingserv.api.validator.BookingValidator.raisedIdempotencyException;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
@Service
public class BookingService {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingDao dao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookingResponse createBooking(Booking booking) throws ValidationException {
        BookingEntity bookingEntity = null;
        final List<BookingEntity> bookingEntities = dao.checkIdempotency(booking);
        if(CollectionUtils.isEmpty(bookingEntities)) {
             bookingEntity = prepareRequestEntity(booking);
        } else {
            raisedIdempotencyException();
        }

        final BookingEntity saveBookingEntity = bookingRepository.save(bookingEntity);
        return prepareResponseEntity(saveBookingEntity);
    }

    public BookingResponses fetchAllBookings(){
        final List<BookingEntity> all = bookingRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        final List<BookingResponse> bR = all.stream().map(p->prepareResponseEntity(p)).collect(Collectors.toList());
        BookingResponses bookingResponses = new BookingResponses();
        bookingResponses.setBookings(bR);
        bookingResponses.setDataCount(bR.size());
        return bookingResponses;
    }

    private BookingEntity prepareRequestEntity(Booking booking) {
        BookingEntity bookingEntity =  new BookingEntity();
        bookingEntity.setFirstName(booking.getFirstName());
        bookingEntity.setLastName(booking.getLastName());
        bookingEntity.setDob(booking.getDob());
        bookingEntity.setCheckinDatetime(booking.getCheckinDatetime());
        bookingEntity.setCheckoutDatetime(booking.getCheckoutDatetime());
        bookingEntity.setTotalPrice(booking.getTotalPrice());
        bookingEntity.setDeposit(booking.getDeposit());
        bookingEntity.setLine1(booking.getAddress().getLine1());
        bookingEntity.setLine2(booking.getAddress().getLine2());
        bookingEntity.setCity(booking.getAddress().getCity());
        bookingEntity.setState(booking.getAddress().getState());
        bookingEntity.setZip_code(booking.getAddress().getZipCode());
        return bookingEntity;
    }

    private BookingResponse prepareResponseEntity(BookingEntity bookingEntity) {
        BookingResponse bookingResponse =  new BookingResponse();

        bookingResponse.setId(bookingEntity.getId());
        bookingResponse.setFirstName(bookingEntity.getFirstName());
        bookingResponse.setLastName(bookingEntity.getLastName());
        bookingResponse.setDob(bookingEntity.getDob());
        bookingResponse.setCheckinDatetime(bookingEntity.getCheckinDatetime());
        bookingResponse.setCheckoutDatetime(bookingEntity.getCheckoutDatetime());
        bookingResponse.setTotalPrice(bookingEntity.getTotalPrice());
        bookingResponse.setDeposit(bookingEntity.getDeposit());
        Address address =  new Address();
        address.setLine1(bookingEntity.getLine1());
        address.setLine2(bookingEntity.getLine2());
        address.setCity(bookingEntity.getCity());
        address.setState(bookingEntity.getState());
        address.setZipCode(bookingEntity.getZip_code());
        bookingResponse.setAddress(address);
        return bookingResponse;
    }

}
