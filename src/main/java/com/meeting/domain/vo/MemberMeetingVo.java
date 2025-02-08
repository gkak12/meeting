package com.meeting.domain.vo;

import lombok.Data;

@Data
public class MemberMeetingVo {

    private Long memberSeq;
    private String memberName;
    private Long meetingSeq;
    private String meetingDateTime;
    private String meetingPlace;
    private String contentName;
}
