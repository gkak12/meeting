package com.meeting.service;

import com.meeting.domain.dto.request.RequestMemberCreateDto;
import com.meeting.domain.dto.request.RequestMemberUpdateDto;
import com.meeting.domain.dto.response.ResponseMemberMeetingVo;
import com.meeting.domain.dto.response.ResponseMemberVo;

import java.util.List;

public interface MemberService {
    List<ResponseMemberVo> findAllMembers();
    ResponseMemberVo findByMemberName(String name);
    List<ResponseMemberMeetingVo> findLatestMeeingEachMember();
    void createMember(RequestMemberCreateDto requestMemberCreateDto);
    void updateMember(RequestMemberUpdateDto requestMemberUpdateDto);
    void deleteMember(Long memberSeq);
}
