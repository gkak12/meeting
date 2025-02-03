package com.meeting.repository;

import com.meeting.domain.dto.MeetingSearchDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;
import com.querydsl.core.Tuple;

import java.util.List;

public interface MeetingRepositoryDsl {

    List<Meeting> findMeetingsByMeetingDate(MeetingSearchDto meetingSearchDto);
    List<MeetingContentVo> findMeetingsByContentName(String contentName);
    MeetingMemberVo findMinMaxMembersMeeting(MeetingSearchDto meetingSearchDto);
    List<Tuple> findMeetingAttendanceByMeetingDate(MeetingSearchDto meetingSearchDto);
}
