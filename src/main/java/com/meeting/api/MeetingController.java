package com.meeting.api;

import com.meeting.domain.dto.request.RequestMeetingSearchDtoRequest;
import com.meeting.domain.dto.response.*;
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
    public ResponseEntity<List<ResponseMeetingVo>> findAllMeetings(){
        return ResponseEntity.ok(meetingService.findAllMeetings());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page")
    public ResponseEntity<ResponseMeetingListVo> findPageMeetings(@ParameterObject RequestMeetingSearchDtoRequest requestMeetingSearchDto){
        return ResponseEntity.ok(meetingService.findPageMeetings(requestMeetingSearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/date-search")
    public ResponseEntity<List<ResponseMeetingVo>> findMeetingsByMeetingDate(@ParameterObject RequestMeetingSearchDtoRequest requestMeetingSearchDto){
        return ResponseEntity.ok(meetingService.findMeetingsByMeetingDate(requestMeetingSearchDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/content-name/{contentName}")
    public ResponseEntity<List<ResponseMeetingContentVo>> findMeetingsByContentName(@PathVariable String contentName){
        if(StringUtils.isBlank(contentName)){
            throw new IllegalArgumentException("contentName is blank.");
        }

        return ResponseEntity.ok(meetingService.findMeetingsByContentName(contentName));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/date-search/min-max")
    public ResponseEntity<ResponseMeetingMemberVo> findMinMaxMembersMeeting(@ParameterObject RequestMeetingSearchDtoRequest requestMeetingSearchDto){
        return ResponseEntity.ok(meetingService.findMinMaxMembersMeeting(requestMeetingSearchDto));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/date-search/attendance")
    public ResponseEntity<List<ResponseMeetingAttendanceVo>> findMeetingAttendanceByMeetingDate(@ParameterObject RequestMeetingSearchDtoRequest requestMeetingSearchDto){
        return ResponseEntity.ok(meetingService.findMeetingAttendanceByMeetingDate(requestMeetingSearchDto));
    }
}
