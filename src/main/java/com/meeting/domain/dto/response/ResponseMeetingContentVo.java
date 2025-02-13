package com.meeting.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseMeetingContentVo {

    private String contentName;
    private String contentCreator;
    private String contentRecommender;
    private LocalDateTime meetingDateTime;
    private String meetingPlace;
}
