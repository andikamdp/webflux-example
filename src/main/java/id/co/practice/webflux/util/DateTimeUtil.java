package id.co.practice.webflux.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy'T'HH:mm:ss.SSS";
    public static final String DEFAULT_BASIC_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss z";

    public static ZonedDateTime getCurrentDate(){
        return ZonedDateTime.now();
    }

    public static DateTimeFormatter getFormatter(String dateFormat){
        return DateTimeFormatter.ofPattern(dateFormat);
    }

    public static String getCurrentDateFormat(){
        return getCurrentDate().format(getFormatter(DEFAULT_DATE_FORMAT));
    }

    public static String getCurrentDateFormat(String format){
        return getCurrentDate().format(getFormatter(format));
    }

    public static String getCurrentDateFormat(ZonedDateTime currentDate, String format){
        return currentDate.format(getFormatter(format));
    }

    public static ZonedDateTime convertStringToZonedDatetime(String date){
        DateTimeFormatter formatter = getFormatter(DEFAULT_BASIC_DATE_FORMAT);

        return ZonedDateTime.parse(date, formatter);
    }
}
