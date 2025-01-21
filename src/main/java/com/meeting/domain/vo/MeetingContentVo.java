package com.meeting.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingContentVo {

    private String contentName;
    private String contentCreator;
    private String contentRecommender;
    private LocalDateTime meetingDateTime;
    private String meetingPlace;
}
