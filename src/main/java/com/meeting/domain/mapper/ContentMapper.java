package com.meeting.domain.mapper;

import com.meeting.domain.dto.ContentDto;
import com.meeting.domain.entity.Content;
import com.meeting.domain.vo.ContentVo;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ContentMapper {
    ContentVo toVo(Content content);
    Content toEntity(ContentDto contentDto);
}
