package com.meeting.api;

import com.meeting.service.MeetingJsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/meeting-json")
@RequiredArgsConstructor
public class MeetingJsonController {

    private final MeetingJsonService meetingJsonService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<Void> createMeetingJson(@RequestParam(value = "jsonFile") MultipartFile multipartFile) throws IOException {
        meetingJsonService.createMeetingJson(multipartFile);
        return ResponseEntity.ok().build();
    }
}
