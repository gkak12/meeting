package com.meeting.domain.dto.response;

import lombok.Data;

@Data
public class ResponseMemberMeetingVo {

    private Long memberSeq;
    private String memberName;
    private Long meetingSeq;
    private String meetingDateTime;
    private String meetingPlace;
    private String contentName;
}
