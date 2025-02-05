package com.meeting.domain.vo;

import lombok.Data;

@Data
public class ContentVo {

    private String contentName;
    private String contentCreator;
    private String contentRecommender;
    private Long selectionNumber;
}
