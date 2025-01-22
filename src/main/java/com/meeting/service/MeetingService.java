package com.meeting.service;

import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;
import com.meeting.domain.vo.MeetingVo;

import java.util.List;

public interface MeetingService {

    List<MeetingVo> findAllMeetings();
    List<MeetingVo> findMeetingsByMeetingDate(MeetingSearchDateDto meetingSearchDateDto);
    List<MeetingContentVo> findMeetingsByContentName(String contentName);
    MeetingMemberVo findMaxMembersMeeting(MeetingSearchDateDto meetingSearchDateDto);
}
