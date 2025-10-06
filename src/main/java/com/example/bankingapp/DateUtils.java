package com.example.bankingapp;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils {

    private DateUtils() {
        // private constructor to prevent instantiation
    }

    /**
     * Formats a date as yyyy-MM-dd.
     *
     * @param date the date to format
     * @return the formatted date string
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * Gets the current timestamp as a string in ISO 8601 format.
     *
     * @return the current timestamp
     */
    public static String getCurrentTimestamp() {
        return java.time.Instant.now().toString();
    }
}
