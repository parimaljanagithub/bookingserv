package com.paypal.bfs.test.bookingserv.api.utils;

import static com.paypal.bfs.test.bookingserv.api.utils.CustomDateUtils.CHECKIN_DATE_FORMAT;
import static com.paypal.bfs.test.bookingserv.api.utils.CustomDateUtils.DOB_DATE_FORMAT;
import org.junit.Assert;
import org.junit.Test;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 18/10/21
 */
public class CustomDateUtilsTest {

    @Test
    public void testValidDateFormat() {
        Assert.assertTrue(CustomDateUtils.isValidFormat("2019-10-23",DOB_DATE_FORMAT));
        Assert.assertTrue(CustomDateUtils.isValidFormat("2019-11-23T23:03:11Z",CHECKIN_DATE_FORMAT));
    }

    @Test
    public void testInvalidDateFormat() {
        Assert.assertFalse(CustomDateUtils.isValidFormat("2019-15-23",DOB_DATE_FORMAT));
        Assert.assertFalse(CustomDateUtils.isValidFormat("2019-11-33T23:03:11Z",CHECKIN_DATE_FORMAT));
    }

    @Test
    public void testStartOfDay() {
        String inputDate = "2019-02-28T23:03:11Z";
        String s = CustomDateUtils.returnStartOfDayFromInput(inputDate, CHECKIN_DATE_FORMAT);
        Assert.assertEquals("2019-02-28T00:00:00Z", s);
    }

    @Test
    public void testEndOfDay() {
        String inputDate = "2013-10-22T01:37:56Z"; //"2019-02-28T13:03:11Z";
        String s = CustomDateUtils.returnEndOfDayFromInput(inputDate, CHECKIN_DATE_FORMAT);
       /// Assert.assertEquals("2019-02-28T23:59:59Z", s);
        Assert.assertEquals("2013-10-22T23:59:59Z", s);
    }
    }
