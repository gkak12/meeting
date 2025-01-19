package com.meeting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {

    private String contentName;
    private String contentCreator;
    private String contentRecommender;
}
