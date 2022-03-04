package ch.uzh.ifi.hase.soprafs22.constant;

import org.hibernate.type.LocalDateTimeType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CurrentDate {

    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String getDateString() {

        // Get current date
        Date currentDate = new Date();
        System.out.println("date : " + dateFormat.format(currentDate));
        System.out.println(dateFormat.format(currentDate));


        // convert date to localdatetime
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime : " + dateFormat8.format(localDateTime));



        // convert LocalDateTime to date
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return (" " + dateFormat.format(currentDatePlusOneDay));

    }



    public static Date getDate(){

        Date currentDate = new Date();


        System.out.println(currentDate);
        return currentDate;
    }


    }
