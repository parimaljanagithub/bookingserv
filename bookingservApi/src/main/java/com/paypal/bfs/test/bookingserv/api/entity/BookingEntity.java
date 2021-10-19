package com.paypal.bfs.test.bookingserv.api.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//import lombok.Ge

/**
 * Copyright (name =c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
@Entity
@Table(name = "BOOKING")
@Getter
@Setter
@NamedQueries({

        @NamedQuery(
                name = "BookingEntity.checkIdempotency" , query = "SELECT b FROM BookingEntity b" +
                " WHERE UPPER(b.firstName) = :firstName AND UPPER(lastName) = :lastName AND dob = :dob AND " +
                " TO_TIMESTAMP(checkinDatetime, 'YYYY-MM-DD HH24:MI:SS' ) >= TO_TIMESTAMP(:checkinDateTime, " +
                "'YYYY-MM-DD HH24:MI:SS' ) AND " +
                "TO_TIMESTAMP(checkoutDatetime, 'YYYY-MM-DD HH24:MI:SS' )<= TO_TIMESTAMP(:checkoutDateTime," +
                "'YYYY-MM-DD HH24:MI:SS' ) AND " +
                "UPPER(line1) = :line1 AND UPPER(city) = :city AND UPPER(state) =:state AND zip_code = :zipCode"
        )
})
@SequenceGenerator(name = "BOOKING_SEQ_GEN", sequenceName = "BOOKING_SEQ_GEN", initialValue = 2000, allocationSize = 1)
public class BookingEntity {
     @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_SEQ_GEN")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "checkin_datetime")
    private String checkinDatetime;

    @Column(name = "checkout_datetime")
    private String checkoutDatetime;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private Integer zip_code;

}


/*  @NamedQuery(
                name = "BookingEntity.checkIdempotency", query = "SELECT b FROM BOOKING b" +
                "WHERE UPPER(first_name) = :firstName AND UPPER(Last_name) = :lastName AND DOB = :dob AND " +
                " TO_TIMESTAMP(CHECKIN_DATETIME, 'YYYY-MM-DD HH24:MI:SS' ) >= TO_TIMESTAMP(:checkinDateTime, 'YYYY-MM-DD HH24:MI:SS' ) AND " +
                "TO_TIMESTAMP(CHECKOUT_DATETIME, 'YYYY-MM-DD HH24:MI:SS' )<= TO_TIMESTAMP(:checkoutDateTime, " +
                "'YYYY-MM-DD HH24:MI:SS' ) AND " +
                "UPPER(line1) = :line1 AND UPPER(city) = :city AND UPPER(STATE) =:state AND zip_code = :zipCode" )
*/