package com.meeting.service.imp;

import com.meeting.common.util.MeetingJsonParser;
import com.meeting.domain.entity.Meeting;
import com.meeting.service.MeetingJsonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingJsonServiceImpl implements MeetingJsonService {

    private final MeetingJsonParser meetingJsonParser;

    @Value("${json.location}")
    private String jsonLocation;

    @Override
    @Transactional
    public void createMeetingJson(MultipartFile multipartFile) throws IOException {
        Objects.requireNonNull(Objects.requireNonNull(multipartFile), "multipartFile is null.");

        if(multipartFile.isEmpty()){
            throw new IllegalArgumentException("multipartFile is empty.");
        }

        String extName = Objects.requireNonNull(multipartFile.getOriginalFilename(), "multipartFile name is null.")
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));

        if(!extName.equals("json")){
            throw new IllegalArgumentException("multipartFile is not json file.");
        }

        File file = new File(jsonLocation.concat(File.separator).concat(multipartFile.getOriginalFilename()));
        multipartFile.transferTo(file);

        Map<LocalDateTime, Meeting> meetingMap = meetingJsonParser.findAllMeetingMap();
        meetingJsonParser.parseJsonFile(file, meetingMap);
    }
}
