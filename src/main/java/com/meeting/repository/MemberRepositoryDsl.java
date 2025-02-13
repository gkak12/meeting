package com.meeting.repository;

import com.meeting.domain.entity.Member;
import com.meeting.domain.dto.response.ResponseMemberMeetingVo;

import java.util.List;

public interface MemberRepositoryDsl {

    Member findByMemberName(String memberName);
    List<ResponseMemberMeetingVo> findLatestMeeingEachMember();
}
