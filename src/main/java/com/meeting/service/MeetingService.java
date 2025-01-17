package com.meeting.service;

import com.meeting.domain.vo.MeetingVo;

import java.util.List;

public interface MeetingService {

    List<MeetingVo> findAllMeetings();
}
