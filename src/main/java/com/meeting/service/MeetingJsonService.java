package com.meeting.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MeetingJsonService {

    void createMeetingJson(MultipartFile multipartFile) throws IOException;
}
