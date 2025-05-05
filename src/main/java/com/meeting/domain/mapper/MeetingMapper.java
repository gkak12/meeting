package com.meeting.domain.mapper;

import com.meeting.domain.dto.request.RequestMeetingDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.dto.response.ResponseMeetingAttendanceVo;
import com.meeting.domain.dto.response.ResponseMeetingVo;
import com.querydsl.core.Tuple;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface MeetingMapper {

    Meeting toEntity(RequestMeetingDto requestMeetingDto);
    ResponseMeetingVo toVo(Meeting meeting);

    @Mapping(target = "meetingDate", expression = "java(localDateTimeToString(meetingTuple.get(0, java.time.LocalDateTime.class)))")
    @Mapping(target = "meetingAttendeeNumber", expression = "java(meetingTuple.get(1, Long.class))")
    ResponseMeetingAttendanceVo toAttendanceVo(Tuple meetingTuple);

    /**
     *  LocalDateTime을 "yyyy-MM-dd" 형식 문자열로 변환하는 메서드
     */
    default String localDateTimeToString(LocalDateTime localDateTime) {
        if(Objects.isNull(localDateTime)){
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(formatter);
    }
}
