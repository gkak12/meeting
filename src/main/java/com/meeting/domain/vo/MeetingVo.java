package com.meeting.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingVo {

    private LocalDateTime meetingDateTime;
    private String meetingPlace;
}
