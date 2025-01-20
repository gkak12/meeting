package com.meeting.domain.mapper;

import com.meeting.domain.dto.MemberCreateDto;
import com.meeting.domain.dto.MemberUpdateDto;
import com.meeting.domain.entity.Member;
import com.meeting.domain.vo.MemberVo;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface MemberMapper {

    MemberVo toVo(Member member);
    Member toCreateEntity(MemberCreateDto memberCreateDto);
    void toUpdateEntity(MemberUpdateDto memberUpdateDto, @MappingTarget Member member);
}
