package com.meeting.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseMeetingVo {

    private LocalDateTime meetingDateTime;
    private String meetingPlace;
}
