

--INSERT INTO BOOKING (id, first_name,  last_name ,  dob ,  checkin_datetime ,
--checkout_datetime ,total_price ,  deposit, line1, line2, city, state, zip_code
-- )VALUES (100, 'parimal', 'jana', '1991-03-06', '2013-10-22T01:37:56:000',
--'2013-10-23T01:37:56:000', 1000, 500, 'kunjapur', 'kolmijore', 'daspur',
--'paschim medinipur', 721211);


CREATE SEQUENCE IF NOT EXISTS BOOKING_SEQ_GEN START WITH 2000 INCREMENT BY 1
NO CACHE;
-- ADDED TO AVOID STATRTUP ERROR
--SELECT * FROM BOOKING;
