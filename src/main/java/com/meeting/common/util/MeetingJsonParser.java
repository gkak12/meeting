package com.meeting.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meeting.common.enums.YnEnums;
import com.meeting.domain.entity.Content;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.entity.MeetingMember;
import com.meeting.domain.entity.Member;
import com.meeting.repository.ContentRepository;
import com.meeting.repository.MeetingMemberRepository;
import com.meeting.repository.MeetingRepository;
import com.meeting.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingJsonParser {

    private final ObjectMapper objectMapper;

    private final ContentRepository contentRepository;
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
    private final MeetingMemberRepository meetingMemberRepository;

    @Value("${json.location}")
    private String jsonLocation;

    @PostConstruct
    public void readJsonFiles(){
        File dir = new File(jsonLocation);
        File[] files = dir.listFiles();

        if(files == null || files.length == 0){
            log.error("json files are empty.");
            return;
        }

        Arrays.stream(files)
                .sorted((file1, file2) -> { // json 파일명 숫자 기준 오름차순 정렬
                    String name1 = file1.getName().replaceAll("\\D", "");
                    String name2 = file2.getName().replaceAll("\\D", "");

                    return Integer.compare(Integer.parseInt(name1), Integer.parseInt(name2));
                })
                .forEach(file -> {  // JSON 파일 파싱
                    parseJsonFile(file.getPath());
                });
    }

    private void parseJsonFile(String filePath){
        try {
            log.info("filePath: {}", filePath);
            InputStream inputStream = new FileInputStream(filePath);

            // JSON 데이터를 JsonNode로 변환
            JsonNode rootNode = objectMapper.readTree(inputStream);

            // 각 요소 파싱
            String contentStr = rootNode.get("content").asText();
            String placeStr = rootNode.get("place").asText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateStr = rootNode.get("date").asText();
            LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter)
                    .atZone(ZoneId.of("Asia/Seoul"))
                    .toLocalDateTime();

            // Meeting entity 생성
            Meeting meeting = new Meeting();
            meeting.setMeetingDateTime(localDateTime);
            meeting.setMeetingPlace(placeStr);
            meetingRepository.save(meeting);

            // Content entity 생성
            Content content = new Content();
            content.setContentName(contentStr);
            content.setMeeting(meeting);
            contentRepository.save(content);

            // attendant/non-attendant 파싱
            JsonNode attendants = rootNode.get("attendant");
            JsonNode nonAttendants = rootNode.get("non-attendant");

            log.info("date: {}", dateStr);
            log.info("content: {}", contentStr);
            log.info("place: {}", placeStr);

            // Member 참석 여부 Map 생성
            Map<String, Boolean> memberMap = new HashMap<>();

            attendants.forEach(item -> memberMap.put(item.asText(), YnEnums.TRUE.getBoolVal()));
            nonAttendants.forEach(item -> memberMap.put(item.asText(), YnEnums.FALSE.getBoolVal()));

            memberMap.keySet()
                    .forEach(item -> {
                        // Member entity 조회
                        Member member = memberRepository.findByMemberName(item);

                        // Member entity 생성
                        if(member == null){
                            Member newMember = new Member();
                            newMember.setMemberName(item);
                            newMember.setIsDeleted(false);
                            memberRepository.save(newMember);

                            member = newMember;
                        }

                        // MeetingMember entity 생성
                        MeetingMember meetingMember = new MeetingMember();
                        meetingMember.setIsAttendance(memberMap.get(item));
                        meetingMember.setMeeting(meeting);
                        meetingMember.setMember(member);
                        meetingMemberRepository.save(meetingMember);
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
