package com.meeting.repository;

import com.meeting.domain.entity.Member;

public interface MemberRepositoryDsl {
    Member findByMemberName(String memberName);
}
