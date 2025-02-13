package com.meeting.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMeetingDto {

    private LocalDateTime meetingDate;
    private String meetingPlace;
}
