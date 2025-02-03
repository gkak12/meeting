package com.meeting.common.bean;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConditionBuilder {

    public static <T> BooleanExpression buildEquals(SimpleExpression<T> path, T value){
        if(path == null){
            return null;
        }

        if(value instanceof String){
            return !((String) value).isEmpty() ? path.eq(value) : null;
        } else if(value instanceof Number){
            if(value instanceof Long && value != null) return path.eq(value);
            else if(value instanceof Integer && value != null) return path.eq(value);
            else if(value instanceof Double && value != null) return path.eq(value);
        } else if(value instanceof Boolean){
            return path.eq(value);
        }

        return null;
    }

    public static BooleanExpression buildStringLike(StringPath path, String value){
        if(path == null){
            return null;
        }

        return StringUtils.isNotBlank(value) ? path.like("%"+value+"%") : null;
    }

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
