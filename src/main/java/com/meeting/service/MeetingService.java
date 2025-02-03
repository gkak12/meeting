package com.meeting.service;

import com.meeting.domain.dto.MeetingSearchDto;
import com.meeting.domain.vo.MeetingAttendanceVo;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;
import com.meeting.domain.vo.MeetingVo;

import java.util.List;

public interface MeetingService {

    List<MeetingVo> findAllMeetings();
    List<MeetingVo> findMeetingsByMeetingDate(MeetingSearchDto meetingSearchDto);
    List<MeetingContentVo> findMeetingsByContentName(String contentName);
    MeetingMemberVo findMinMaxMembersMeeting(MeetingSearchDto meetingSearchDto);
    List<MeetingAttendanceVo>  findMeetingAttendanceByMeetingDate(MeetingSearchDto meetingSearchDto);
}
