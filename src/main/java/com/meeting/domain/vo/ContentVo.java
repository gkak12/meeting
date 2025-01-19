package com.meeting.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentVo {

    private String contentName;
    private String contentCreator;
    private String contentRecommender;
}
