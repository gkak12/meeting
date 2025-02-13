package com.meeting.service;

import com.meeting.domain.dto.request.RequestMeetingSearchDtoRequest;
import com.meeting.domain.dto.response.*;

import java.util.List;

public interface MeetingService {

    List<ResponseMeetingVo> findAllMeetings();
    ResponseMeetingListVo findPageMeetings(RequestMeetingSearchDtoRequest requestMeetingSearchDto);
    List<ResponseMeetingVo> findMeetingsByMeetingDate(RequestMeetingSearchDtoRequest requestMeetingSearchDto);
    List<ResponseMeetingContentVo> findMeetingsByContentName(String contentName);
    ResponseMeetingMemberVo findMinMaxMembersMeeting(RequestMeetingSearchDtoRequest requestMeetingSearchDto);
    List<ResponseMeetingAttendanceVo>  findMeetingAttendanceByMeetingDate(RequestMeetingSearchDtoRequest requestMeetingSearchDto);
}
