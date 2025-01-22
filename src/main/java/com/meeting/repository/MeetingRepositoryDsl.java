package com.meeting.repository;

import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;

import java.util.List;

public interface MeetingRepositoryDsl {

    List<Meeting> findMeetingsByMeetingDate(MeetingSearchDateDto meetingSearchDateDto);
    List<MeetingContentVo> findMeetingsByContentName(String contentName);
    MeetingMemberVo findMinMaxMembersMeeting(MeetingSearchDateDto meetingSearchDateDto);
}
