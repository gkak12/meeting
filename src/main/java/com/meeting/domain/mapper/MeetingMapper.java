package com.meeting.domain.mapper;

import com.meeting.domain.dto.MeetingDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.vo.MeetingVo;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface MeetingMapper {
    MeetingVo toVo(Meeting meeting);
    Meeting toEntity(MeetingDto meetingDto);
}
