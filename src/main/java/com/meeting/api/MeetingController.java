package com.meeting.api;

import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.vo.MeetingVo;
import com.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<MeetingVo>> findAllMeetings(){
        return ResponseEntity.ok(meetingService.findAllMeetings());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/date-search")
    public ResponseEntity<List<MeetingVo>> findMeetingsByMeetingDate(@ParameterObject MeetingSearchDateDto meetingSearchDateDto){
        return ResponseEntity.ok(meetingService.findMeetingsByMeetingDate(meetingSearchDateDto));
    }
}
