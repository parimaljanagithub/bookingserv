package com.paypal.bfs.test.bookingserv.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static ch.qos.logback.core.CoreConstants.SYSOUT;

/**
 * Copyright (c) 1997 - 2020 Rakuten, Inc. All Rights Reserved.
 * User: parimal.jana
 * Date: 17/10/21
 */
public class CustomDateUtils {

    public static String DOB_DATE_FORMAT = "yyyy-MM-dd";
    public static String CHECKIN_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static boolean isValidFormat(String inputDate, String datePattern) {
        DateFormat df = new SimpleDateFormat(datePattern);
        df.setLenient(false);

        try {
            df.parse(inputDate);
        } catch (ParseException e) {
            // e.printStackTrace();
            return false;
        }

        return true;
    }

    public static String returnStartOfDayFromInput(String inputDate, String datePattern) {
        DateFormat df = new SimpleDateFormat(datePattern);
        df.setLenient(false);

        try {
           /*
            LocalDateTime ldt = LocalDateTime.ofInstant(df.parse(inputDate).toInstant(), ZoneId.of("UTC"));

            LocalDateTime of = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MIDNIGHT);
            */
            Date parse = df.parse(inputDate);
            LocalDateTime d1= new java.sql.Timestamp(parse.getTime()).toLocalDateTime();
            LocalDateTime d2 = LocalDateTime.of(d1.toLocalDate(), LocalTime.MIDNIGHT);
            return formateDate(d2, datePattern);

        } catch (ParseException e) {
            e.printStackTrace();

        }

        return null;

    }

    public static String returnEndOfDayFromInput(String inputDate, String datePattern) {
        DateFormat df = new SimpleDateFormat(datePattern);
        df.setLenient(false);

        try {
            Date parse = df.parse(inputDate);
            LocalDateTime d1= new java.sql.Timestamp(parse.getTime()).toLocalDateTime();
            LocalDateTime d2 = LocalDateTime.of(d1.toLocalDate(), LocalTime.MAX);
            return formateDate(d2, datePattern);

        } catch (ParseException e) {
            e.printStackTrace();

        }

        return null;
    }


    private static String formateDate(LocalDateTime localDateTime, String datePattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String format = localDateTime.format(formatter);
        System.out.println(format);
        return format;
    }
/*
    public static void main(String[] args) {
        String input = "2019-21-23T23:03:11Z";
       // System.out.println(isValidFormat(input,CHECKIN_DATE_FORMAT));
        String input2 = "2019-02-29";
        System.out.println(isValidFormat(input2,DOB_DATE_FORMAT));
    } */
}
