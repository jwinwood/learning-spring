package com.example.learningspring.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate createLocalDateFromDateString(String dateString) {
        LocalDate date;
        if (dateString != null && !dateString.isBlank()) {
            date = LocalDate.parse(dateString, DATE_FORMATTER);
        } else {
            date = LocalDate.now();
        }

        return date;
    }
}
