package com.meeting.repository;

import com.meeting.domain.dto.MeetingSearchDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MeetingRepositoryDsl {

    Page<Meeting> findMeetingsPaging(MeetingSearchDto dto);
    List<Meeting> findMeetingsByMeetingDate(MeetingSearchDto meetingSearchDto);
    List<MeetingContentVo> findMeetingsByContentName(String contentName);
    MeetingMemberVo findMinMaxMembersMeeting(MeetingSearchDto meetingSearchDto);
    List<Tuple> findMeetingAttendanceByMeetingDate(MeetingSearchDto meetingSearchDto);
}
