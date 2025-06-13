package com.meeting.repository.impl;

import com.meeting.common.enums.YnEnums;
import com.meeting.domain.dto.response.ResponseMemberMeetingVo;
import com.meeting.domain.dto.response.ResponseMemberVo;
import com.meeting.domain.entity.Member;
import com.meeting.repository.MemberRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
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
    public List<ResponseMemberMeetingVo> findLatestMeeingEachMember() {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(member.isDeleted.eq(YnEnums.FALSE.getBoolVal()))
            .and(meetingMember.isAttendance.eq(YnEnums.TRUE.getBoolVal()));

        return jpaQueryFactory
                .select(Projections.fields(
                    ResponseMemberMeetingVo.class,
                    member.memberSeq,
                    member.memberName,
                    meetingMember.meeting.meetingSeq.max().as("meetingSeq")
                ))
                .from(member)
                .leftJoin(meetingMember)
                .on(member.memberSeq.eq(meetingMember.member.memberSeq))
                .where(builder)
                .groupBy(member.memberSeq)
                .orderBy(member.memberName.asc())
                .fetch();
    }

    @Override
    public List<ResponseMemberVo> findMeetingCountEachMember() {
        return jpaQueryFactory
                .select(
                    Projections.fields(
                        ResponseMemberVo.class,
                        member.memberName,
                        Expressions.numberTemplate(
                            Long.class,
                    "SUM(CASE WHEN {0} IS TRUE THEN 1 ELSE 0 END)",
                            meetingMember.isAttendance
                        ).as("meetingAttendancesNumber")
                    )
                )
                .from(member)
                .leftJoin(meetingMember)
                .on(member.memberSeq.eq(meetingMember.member.memberSeq))
                .groupBy(member.memberSeq)
                .orderBy(member.memberName.asc())
                .fetch();
    }
}
