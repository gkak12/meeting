package com.meeting.repository;

import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.entity.Meeting;

import java.util.List;

public interface MeetingRepositoryDsl {
    List<Meeting> findMeetingsByMeetingDate(MeetingSearchDateDto meetingSearchDateDto);
}
