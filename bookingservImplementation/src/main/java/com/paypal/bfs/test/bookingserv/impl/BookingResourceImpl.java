package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.api.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponse;
import com.paypal.bfs.test.bookingserv.api.model.BookingResponses;
import com.paypal.bfs.test.bookingserv.api.service.BookingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.paypal.bfs.test.bookingserv.api.validator.BookingValidator.validateCreateRequest;

@RestController
public class BookingResourceImpl implements BookingResource {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    BookingService bookingService;

    @Override
    public ResponseEntity<BookingResponse> create(Booking booking) throws ValidationException {
        logger.info("create booking endpoint(/v1/bfs/booking) is called");
        validateCreateRequest(booking);
        logger.info("validation passed successfully");
        final BookingResponse bookingResponse = bookingService.createBooking(booking);
        logger.info("booking confirmed");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);

    }

    @Override
    public ResponseEntity<BookingResponses> getAllBookings() {
        logger.info("Get all booking endpoint(/v1/bfs/booking/list) called");
        final BookingResponses bookingResponses = bookingService.fetchAllBookings();
        return ResponseEntity.status(HttpStatus.OK).body(bookingResponses);
    }


}
