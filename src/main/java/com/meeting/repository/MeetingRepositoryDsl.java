package com.meeting.repository;

import com.meeting.domain.dto.request.RequestMeetingSearchDtoRequest;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.dto.response.ResponseMeetingContentVo;
import com.meeting.domain.dto.response.ResponseMeetingMemberVo;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MeetingRepositoryDsl {

    Page<Meeting> findMeetingsPaging(RequestMeetingSearchDtoRequest dto);
    List<Meeting> findMeetingsByMeetingDate(RequestMeetingSearchDtoRequest requestMeetingSearchDto);
    List<ResponseMeetingContentVo> findMeetingsByContentName(String contentName);
    ResponseMeetingMemberVo findMinMaxMembersMeeting(RequestMeetingSearchDtoRequest requestMeetingSearchDto);
    List<Tuple> findMeetingAttendanceByMeetingDate(RequestMeetingSearchDtoRequest requestMeetingSearchDto);
    List<Meeting> findMeetingContent(List<Long> meetingSeqs);
}
