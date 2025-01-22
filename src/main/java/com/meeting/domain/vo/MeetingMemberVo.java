package com.meeting.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MeetingMemberVo {

    private Long meetingSeq;
    private String contentName;
    private LocalDateTime meetingDateTime;
    private String meetingPlace;
    private Long num;
    private List<MeetingMemberVo.Member> members;

    @Data
    public static class Member{
        private String memberName;
    }
}
