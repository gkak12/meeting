package com.meeting.service;

import com.meeting.domain.vo.MemberVo;

import java.util.List;

public interface MemberService {
    List<MemberVo> findAllMembers();
    MemberVo findByMemberName(String name);
}
