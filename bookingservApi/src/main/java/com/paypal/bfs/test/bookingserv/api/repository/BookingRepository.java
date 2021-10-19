package com.paypal.bfs.test.bookingserv.api.repository;


import com.paypal.bfs.test.bookingserv.api.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

}
