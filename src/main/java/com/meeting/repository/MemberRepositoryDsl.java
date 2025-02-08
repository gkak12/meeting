package com.meeting.repository;

import com.meeting.domain.entity.Member;
import com.meeting.domain.vo.MemberMeetingVo;

import java.util.List;

public interface MemberRepositoryDsl {

    Member findByMemberName(String memberName);
    List<MemberMeetingVo> findLatestMeeingEachMember();
}
