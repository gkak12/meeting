package com.meeting.repository.impl;

import com.meeting.domain.entity.Member;
import com.meeting.repository.MemberRepositoryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.meeting.domain.entity.QMember.member;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryDslImpl implements MemberRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Member findByMemberName(String memberName) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.memberName.eq(memberName))
                .fetchOne();
    }
}
