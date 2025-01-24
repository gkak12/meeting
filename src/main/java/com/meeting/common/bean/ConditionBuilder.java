package com.meeting.common.bean;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConditionBuilder {

    public static BooleanExpression buildDateBetween(DateTimePath<LocalDateTime> path, LocalDate startDate, LocalDate endDate) {
        if(path == null){
            return null;
        }

        if(startDate != null && endDate != null && startDate.isBefore(endDate)) {
            return path.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        } else if(startDate != null && endDate == null){
            return path.goe(startDate.atStartOfDay());
        } else if(startDate == null && endDate != null){
            return path.loe(endDate.atTime(23, 59, 59));
        } else{
            return null;
        }
    }

    public static BooleanExpression buildDateTimeBetween(DateTimePath<LocalDateTime> path, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if(path == null){
            return null;
        }

        if(startDateTime != null && endDateTime != null && startDateTime.isBefore(endDateTime)) {
            return path.between(startDateTime, endDateTime);
        } else if(startDateTime != null && endDateTime == null){
            return path.goe(startDateTime);
        } else if(startDateTime == null && endDateTime != null){
            return path.loe(endDateTime);
        } else{
            return null;
        }
    }
}
