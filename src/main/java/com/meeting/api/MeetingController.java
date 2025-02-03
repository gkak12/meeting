package com.meeting.api;

import com.meeting.domain.dto.MeetingSearchDto;
import com.meeting.domain.vo.*;
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
    @GetMapping("/page")
    public ResponseEntity<MeetingListVo> findPageMeetings(@ParameterObject MeetingSearchDto meetingSearchDto){
        return ResponseEntity.ok(meetingService.findPageMeetings(meetingSearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/date-search")
    public ResponseEntity<List<MeetingVo>> findMeetingsByMeetingDate(@ParameterObject MeetingSearchDto meetingSearchDto){
        return ResponseEntity.ok(meetingService.findMeetingsByMeetingDate(meetingSearchDto));
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
    public ResponseEntity<MeetingMemberVo> findMinMaxMembersMeeting(@ParameterObject MeetingSearchDto meetingSearchDto){
        return ResponseEntity.ok(meetingService.findMinMaxMembersMeeting(meetingSearchDto));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/date-search/attendance")
    public ResponseEntity<List<MeetingAttendanceVo>> findMeetingAttendanceByMeetingDate(@ParameterObject MeetingSearchDto meetingSearchDto){
        return ResponseEntity.ok(meetingService.findMeetingAttendanceByMeetingDate(meetingSearchDto));
    }
}
