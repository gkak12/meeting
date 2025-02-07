package com.meeting.service.imp;

import com.meeting.domain.dto.MeetingSearchDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.mapper.MeetingMapper;
import com.meeting.domain.vo.*;
import com.meeting.repository.MeetingRepository;
import com.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;

    @Override
    public List<MeetingVo> findAllMeetings() {
        return meetingRepository.findAll().stream()
            .map(meetingMapper::toVo)
            .toList();
    }

    @Override
    public MeetingListVo findPageMeetings(MeetingSearchDto meetingSearchDto) {
        Page<Meeting> page = meetingRepository.findMeetingsPaging(meetingSearchDto);

        PageVo pageVo = PageVo.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .build();

        List<MeetingVo> list = page.get().toList().stream()
                .map(meetingMapper::toVo)
                .toList();

        return MeetingListVo.builder()
                .page(pageVo)
                .list(list)
                .build();
    }

    @Override
    public List<MeetingVo> findMeetingsByMeetingDate(MeetingSearchDto meetingSearchDto) {
        return meetingRepository.findMeetingsByMeetingDate(meetingSearchDto).stream()
                .map(meetingMapper::toVo)
                .toList();
    }

    @Override
    public List<MeetingContentVo> findMeetingsByContentName(String contentName) {
        return meetingRepository.findMeetingsByContentName(contentName);
    }

    @Override
    public MeetingMemberVo findMinMaxMembersMeeting(MeetingSearchDto meetingSearchDto) {
        return meetingRepository.findMinMaxMembersMeeting(meetingSearchDto);
    }

    @Override
    public List<MeetingAttendanceVo> findMeetingAttendanceByMeetingDate(MeetingSearchDto meetingSearchDto) {
        return meetingRepository.findMeetingAttendanceByMeetingDate(meetingSearchDto).stream()
                .map(meetingMapper::toAttendanceVo)
                .toList();
    }
}
