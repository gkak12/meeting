package com.meeting.domain.dto.response;

import lombok.Data;

@Data
public class ResponseContentVo {

    private String contentName;
    private String contentCreator;
    private String contentRecommender;
    private Long selectionNumber;
}
