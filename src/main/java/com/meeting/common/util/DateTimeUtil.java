package com.meeting.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final String timeZoneName = "Asia/Seoul";

    public static LocalTime getLocalTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalTime();
    }

    public static String convertLocalTimeToString(String pattern, LocalTime localTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localTime);
    }

    public static LocalDate getLocalDate(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDate();
    }

    public static String convertLocalDateToString(String pattern, LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDate);
    }

    public static LocalDateTime getLocalDateTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDateTime();
    }

    public static String convertLocalDateTimeToString(String pattern, LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDateTime);
    }

    public static LocalDateTime getQuarterStartDateTime(int year, int quarter){
        int startMonth = (quarter - 1) * 3 + 1;
        LocalDateTime startDateTime = LocalDateTime.of(year, startMonth, 1, 0, 0, 0);

        return startDateTime;
    }

    public static LocalDateTime getQuarterEndDateTime(int year, int quarter){
        int endMonth = quarter * 3;
        YearMonth yearMonth = YearMonth.of(year, endMonth);
        LocalDate endDate = yearMonth.atEndOfMonth();
        LocalDateTime endDateTime = LocalDateTime.of(endDate, java.time.LocalTime.of(23, 59, 59));

        return endDateTime;
    }
}
