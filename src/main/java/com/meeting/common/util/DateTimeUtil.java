package com.meeting.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtil {

    @Value("${app.timezone}")
    private String timeZoneName;

    public LocalTime getLocalTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalTime();
    }

    public String convertLocalTimeToString(String pattern, LocalTime localTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localTime);
    }

    public LocalDate getLocalDate(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDate();
    }

    public String convertLocalDateToString(String pattern, LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDate);
    }

    public LocalDateTime getLocalDateTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDateTime();
    }

    public String convertLocalDateTimeToString(String pattern, LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDateTime);
    }

    public LocalDateTime getQuarterStartDateTime(int year, int quarter){
        int startMonth = (quarter - 1) * 3 + 1;
        LocalDateTime startDateTime = LocalDateTime.of(year, startMonth, 1, 0, 0, 0);

        return startDateTime;
    }

    public LocalDateTime getQuarterEndDateTime(int year, int quarter){
        int endMonth = quarter * 3;
        YearMonth yearMonth = YearMonth.of(year, endMonth);
        LocalDate endDate = yearMonth.atEndOfMonth();
        LocalDateTime endDateTime = LocalDateTime.of(endDate, java.time.LocalTime.of(23, 59, 59));

        return endDateTime;
    }
}
