package com.meeting.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseMeetingMemberVo {

    private Long meetingSeq;
    private String contentName;
    private LocalDateTime meetingDateTime;
    private String meetingPlace;
    private Long num;
    private List<ResponseMeetingMemberVo.Member> members;

    @Data
    public static class Member{
        private String memberName;
    }
}
