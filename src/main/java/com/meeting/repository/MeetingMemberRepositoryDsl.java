package com.meeting.repository;

import com.meeting.domain.dto.response.ResponseMemberVo;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingMemberRepositoryDsl {

    List<ResponseMemberVo> findTop10AttendanceMemberByQuarter(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
