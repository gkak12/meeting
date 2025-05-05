package com.meeting.domain.dto.response;

import lombok.Data;

@Data
public class ResponseMeetingAttendanceVo {

    private String meetingDate;
    private Long meetingAttendeesNumber;
}
