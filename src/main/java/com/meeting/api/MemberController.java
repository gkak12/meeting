package com.meeting.api;

import com.meeting.common.annotation.ValidSeq;
import com.meeting.common.annotation.ValidString;
import com.meeting.common.annotation.ValidYearQuarter;
import com.meeting.domain.dto.request.RequestMemberCreateDto;
import com.meeting.domain.dto.request.RequestMemberUpdateDto;
import com.meeting.domain.dto.response.ResponseMemberMeetingVo;
import com.meeting.domain.dto.response.ResponseMemberVo;
import com.meeting.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ResponseMemberVo>> findAllMembers(){
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/seq/{seq}")
    public ResponseEntity<ResponseMemberVo> findByMemberSeq(@PathVariable @Valid @ValidSeq Long seq){
        return ResponseEntity.ok(memberService.findByMemberSeq(seq));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseMemberVo> findByMemberName(@PathVariable @Valid @ValidString String name){
        return ResponseEntity.ok(memberService.findByMemberName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/latest-meeing-each-member")
    public ResponseEntity<List<ResponseMemberMeetingVo>> findLatestMeeingEachMember(){
        return ResponseEntity.ok(memberService.findLatestMeeingEachMember());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/latest-meeing-each-member/csv")
    public ResponseEntity<byte[]> downloadLatestMeeingEachMember(){
        String fileName = "LatestMeeingEachMember.csv";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(memberService.downloadLatestMeeingEachMember());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/meeting-count-each-member")
    public ResponseEntity<List<ResponseMemberVo>> findMeetingCountEachMember(){
        return ResponseEntity.ok(memberService.findMeetingCountEachMember());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody @Valid RequestMemberCreateDto requestMemberCreateDto){
        memberService.createMember(requestMemberCreateDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public ResponseEntity<Void> updateMember(@RequestBody @Valid RequestMemberUpdateDto requestMemberUpdateDto){
        memberService.updateMember(requestMemberUpdateDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{memberSeq}")
    public ResponseEntity<Void> deleteMember(@PathVariable @Valid @ValidSeq Long memberSeq){
        memberService.deleteMember(memberSeq);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/year-quarter/{yearQuarter}")
    public ResponseEntity<List<ResponseMemberVo>> findTop10AttendanceMemberByQuarter(@PathVariable @Valid @ValidYearQuarter String yearQuarter){
        return ResponseEntity.ok(memberService.findTop10AttendanceMemberByQuarter(yearQuarter));
    }
}
