package com.meeting.api;

import com.meeting.domain.vo.MemberVo;
import com.meeting.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<MemberVo>> findAllMembers(){
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public ResponseEntity<MemberVo> findByMemberName(@PathVariable String name) throws Exception {
        if(StringUtils.isBlank(name)){
            throw new Exception("name is empty.");
        }

        return ResponseEntity.ok(memberService.findByMemberName(name));
    }
}
