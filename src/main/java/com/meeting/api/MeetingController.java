package com.meeting.api;

import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;
import com.meeting.domain.vo.MeetingVo;
import com.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/content-name/{contentName}")
    public ResponseEntity<List<MeetingContentVo>> findMeetingsByContentName(@PathVariable String contentName){
        if(StringUtils.isBlank(contentName)){
            throw new IllegalArgumentException("contentName is blank.");
        }

        return ResponseEntity.ok(meetingService.findMeetingsByContentName(contentName));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/date-search/min-max")
    public ResponseEntity<MeetingMemberVo> findMinMaxMembersMeeting(@ParameterObject MeetingSearchDateDto meetingSearchDateDto){
        return ResponseEntity.ok(meetingService.findMinMaxMembersMeeting(meetingSearchDateDto));
    }
}
