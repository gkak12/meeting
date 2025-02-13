package com.meeting.domain.mapper;

import com.meeting.domain.dto.request.RequestContentDto;
import com.meeting.domain.entity.Content;
import com.meeting.domain.dto.response.ResponseContentVo;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ContentMapper {

    ResponseContentVo toVo(Content content);
    Content toEntity(RequestContentDto requestContentDto);
}
