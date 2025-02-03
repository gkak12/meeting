package com.meeting.service.imp;

import com.meeting.domain.dto.MeetingSearchDto;
import com.meeting.domain.mapper.MeetingMapper;
import com.meeting.domain.vo.MeetingAttendanceVo;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;
import com.meeting.domain.vo.MeetingVo;
import com.meeting.repository.MeetingRepository;
import com.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
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
