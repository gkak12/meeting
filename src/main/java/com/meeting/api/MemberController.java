package com.meeting.api;

import com.meeting.domain.dto.MemberCreateDto;
import com.meeting.domain.dto.MemberUpdateDto;
import com.meeting.domain.vo.MemberVo;
import com.meeting.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<MemberVo>> findAllMembers(){
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public ResponseEntity<MemberVo> findByMemberName(@PathVariable String name){
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("name is empty.");
        }

        return ResponseEntity.ok(memberService.findByMemberName(name));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody @Valid MemberCreateDto memberCreateDto){
        memberService.createMember(memberCreateDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public ResponseEntity<Void> updateMember(@RequestBody @Valid MemberUpdateDto memberUpdateDto){
        memberService.updateMember(memberUpdateDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{memberSeq}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberSeq){
        if(memberSeq == null || memberSeq <= 1){
            throw new IllegalArgumentException("memberSeq is null or less than 1.");
        }

        memberService.deleteMember(memberSeq);
        return ResponseEntity.ok().build();
    }
}
