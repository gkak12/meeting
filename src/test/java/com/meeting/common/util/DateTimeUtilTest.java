package com.meeting.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilTest {

    private DateTimeUtil dateTimeUtil;

    @BeforeEach
    void setup(){
        dateTimeUtil = new DateTimeUtil();
    }

    @Test
    void getQuarterStartDateTime_테스트(){
        // Given
        int year = 2025;
        int quarter = 1;

        // When
        LocalDateTime startDateTime = dateTimeUtil.getQuarterStartDateTime(year, quarter);
        LocalDateTime expectDateTime = LocalDateTime.of(year, 1, 1, 0,0,0);

        // Then
        assertEquals(expectDateTime, startDateTime);
    }

    @Test
    void getQuarterEndDateTime_테스트(){
        // Given
        int year = 2025;
        int quarter = 1;

        // When
        LocalDateTime endDateTime = dateTimeUtil.getQuarterEndDateTime(year, quarter);
        LocalDateTime expectDateTime = LocalDateTime.of(year, 3, 31, 23,59,59);

        // Then
        assertEquals(expectDateTime, endDateTime);
    }
}
