package com.meeting.service.imp;

import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.mapper.MeetingMapper;
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
public class MeetingServiceImp implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    @Override
    public List<MeetingVo> findAllMeetings() {
            return meetingRepository.findAll().stream()
                .map(meetingMapper::toVo)
                .toList();
    }

    @Override
    public List<MeetingVo> findMeetingsByMeetingDate(MeetingSearchDateDto meetingSearchDateDto) {
        return meetingRepository.findMeetingsByMeetingDate(meetingSearchDateDto).stream()
                .map(meetingMapper::toVo)
                .toList();
    }
}
