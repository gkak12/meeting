package com.meeting.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MeetingVo {

    private LocalDate meetingDate;
    private String meetingPlace;
}
