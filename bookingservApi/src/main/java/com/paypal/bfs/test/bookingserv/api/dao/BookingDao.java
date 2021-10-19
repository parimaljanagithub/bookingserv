package com.paypal.bfs.test.bookingserv.api.dao;

import com.paypal.bfs.test.bookingserv.api.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.utils.CustomDateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.paypal.bfs.test.bookingserv.api.utils.CustomDateUtils.CHECKIN_DATE_FORMAT;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 19/10/21
 */
@Repository
public class BookingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<BookingEntity> checkIdempotency(Booking booking){
        try {
            return entityManager.createNamedQuery("BookingEntity.checkIdempotency", BookingEntity.class)
                   .setParameter("firstName", booking.getFirstName().toUpperCase())
                     .setParameter("lastName", booking.getLastName().toUpperCase())
                      .setParameter("dob", booking.getDob().toUpperCase())
                      .setParameter("checkinDateTime", CustomDateUtils.returnStartOfDayFromInput(
                             booking.getCheckinDatetime(), CHECKIN_DATE_FORMAT))
                     .setParameter("checkoutDateTime", CustomDateUtils.returnEndOfDayFromInput(
                             booking.getCheckoutDatetime(), CHECKIN_DATE_FORMAT))
                     .setParameter("line1", booking.getAddress().getLine1().toUpperCase())
                     .setParameter("city", booking.getAddress().getCity().toUpperCase())
                     .setParameter("state", booking.getAddress().getState().toUpperCase())
                     .setParameter("zipCode", booking.getAddress().getZipCode())
                    .getResultList();
        } finally {
            entityManager.clear();
        }


    }
}
