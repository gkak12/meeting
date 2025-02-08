package com.meeting.service;

import com.meeting.domain.dto.MemberCreateDto;
import com.meeting.domain.dto.MemberUpdateDto;
import com.meeting.domain.vo.MemberMeetingVo;
import com.meeting.domain.vo.MemberVo;

import java.util.List;

public interface MemberService {
    List<MemberVo> findAllMembers();
    MemberVo findByMemberName(String name);
    List<MemberMeetingVo> findLatestMeeingEachMember();
    void createMember(MemberCreateDto memberCreateDto);
    void updateMember(MemberUpdateDto memberUpdateDto);
    void deleteMember(Long memberSeq);
}
