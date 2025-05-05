package com.meeting.domain.mapper;

import com.meeting.domain.dto.request.RequestMemberCreateDto;
import com.meeting.domain.dto.request.RequestMemberUpdateDto;
import com.meeting.domain.entity.Member;
import com.meeting.domain.dto.response.ResponseMemberVo;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface MemberMapper {

    @Mapping(target = "meetingAttendancesNumber", ignore = true)
    ResponseMemberVo toVo(Member member);

    Member toCreateEntity(RequestMemberCreateDto requestMemberCreateDto);
    void toUpdateEntity(RequestMemberUpdateDto requestMemberUpdateDto, @MappingTarget Member member);
}
