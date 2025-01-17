package com.meeting.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
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

    @PostConstruct
    public void readJsonFiles(){
        File dir = new File("E:\\json");
        File[] files = dir.listFiles();

        Arrays.sort(files, (file1, file2) -> {  // json 파일명 숫자 기준 오름차순 정렬
            String name1 = file1.getName().replaceAll("\\D", "");
            String name2 = file2.getName().replaceAll("\\D", "");

            return Integer.compare(Integer.parseInt(name1), Integer.parseInt(name2));
        });

        for(File file : files){
            parseJsonFile(file.getPath());
        }
    }

    private void parseJsonFile(String filePath){
        try {
            log.info("filePath: {}", filePath);
            InputStream inputStream = new FileInputStream(filePath);

            // JSON 데이터를 JsonNode로 변환
            JsonNode rootNode = objectMapper.readTree(inputStream);

            // 각 요소 파싱
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateStr = rootNode.get("date").asText();
            String contentStr = rootNode.get("content").asText();
            String placeStr = rootNode.get("place").asText();

            // Meeting entity 생성
            Meeting meeting = new Meeting();
            meeting.setMeetingDate(LocalDateTime.parse(dateStr, formatter));
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

            attendants.forEach(item -> {
                memberMap.put(item.asText(), true);
            });

            nonAttendants.forEach(item -> {
                memberMap.put(item.asText(), false);
            });

            memberMap.keySet().stream()
                .forEach(item -> {
                    // Member entity 조회
                    Member member = memberRepository.findByMemberName(item);

                    // Member entity 생성
                    if(member == null){
                        Member newMember = new Member();
                        newMember.setMemberName(item);
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
