package com.meeting.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContentDto {

    private String contentName;
    private String contentCreator;
    private String contentRecommender;
}
