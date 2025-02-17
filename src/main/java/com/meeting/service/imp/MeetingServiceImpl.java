package com.meeting.service.imp;

import com.meeting.domain.dto.request.RequestMeetingSearchDtoRequest;
import com.meeting.domain.dto.response.*;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.mapper.MeetingMapper;
import com.meeting.repository.MeetingRepository;
import com.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingServiceImpl implements MeetingService {

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMeetingVo> findAllMeetings() {
        return meetingRepository.findAll().stream()
            .map(meetingMapper::toVo)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseMeetingListVo findPageMeetings(RequestMeetingSearchDtoRequest requestMeetingSearchDto) {
        Page<Meeting> page = meetingRepository.findMeetingsPaging(requestMeetingSearchDto);

        ResponsePageVo responsePageVo = ResponsePageVo.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .build();

        List<ResponseMeetingVo> list = page.get().toList().stream()
                .map(meetingMapper::toVo)
                .toList();

        return ResponseMeetingListVo.builder()
                .page(responsePageVo)
                .list(list)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMeetingVo> findMeetingsByMeetingDate(RequestMeetingSearchDtoRequest requestMeetingSearchDto) {
        return meetingRepository.findMeetingsByMeetingDate(requestMeetingSearchDto).stream()
                .map(meetingMapper::toVo)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMeetingContentVo> findMeetingsByContentName(String contentName) {
        return meetingRepository.findMeetingsByContentName(contentName);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseMeetingMemberVo findMinMaxMembersMeeting(RequestMeetingSearchDtoRequest requestMeetingSearchDto) {
        return meetingRepository.findMinMaxMembersMeeting(requestMeetingSearchDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMeetingAttendanceVo> findMeetingAttendanceByMeetingDate(RequestMeetingSearchDtoRequest requestMeetingSearchDto) {
        return meetingRepository.findMeetingAttendanceByMeetingDate(requestMeetingSearchDto).stream()
                .map(meetingMapper::toAttendanceVo)
                .toList();
    }
}
