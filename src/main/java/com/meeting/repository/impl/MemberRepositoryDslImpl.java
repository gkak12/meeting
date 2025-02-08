package com.meeting.repository.impl;

import com.meeting.domain.entity.Member;
import com.meeting.domain.vo.MemberMeetingVo;
import com.meeting.repository.MemberRepositoryDsl;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.meeting.domain.entity.QMeetingMember.meetingMember;
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

    @Override
    public List<MemberMeetingVo> findLatestMeeingEachMember() {
        return jpaQueryFactory
                .select(Projections.fields(
                    MemberMeetingVo.class,
                    member.memberSeq,
                    member.memberName,
                    meetingMember.meeting.meetingSeq.max().as("meetingSeq")
                ))
                .from(member)
                .leftJoin(meetingMember)
                .on(member.memberSeq.eq(meetingMember.member.memberSeq))
                .where(meetingMember.isAttendance.eq(true))
                .groupBy(member.memberSeq)
                .fetch();
    }
}
