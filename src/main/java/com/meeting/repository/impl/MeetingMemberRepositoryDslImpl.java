package com.meeting.repository.impl;

import com.meeting.common.util.ConditionBuilder;
import com.meeting.domain.dto.response.ResponseMemberVo;
import com.meeting.repository.MeetingMemberRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.meeting.domain.entity.QMeeting.meeting;
import static com.meeting.domain.entity.QMeetingMember.meetingMember;
import static com.meeting.domain.entity.QMember.member;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MeetingMemberRepositoryDslImpl implements MeetingMemberRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ResponseMemberVo> findTop10AttendanceMemberByQuarter(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(ConditionBuilder.buildEquals(meetingMember.isAttendance, true))
            .and(ConditionBuilder.buildDateTimeBetween(meeting.meetingDateTime, startDateTime, endDateTime));

        return jpaQueryFactory
                .select(Projections.fields(
                    ResponseMemberVo.class,
                    member.memberName,
                    member.memberName.count().as("meetingAttendancesNumber")
                ))
                .from(meetingMember)
                .leftJoin(member).on(meetingMember.member.memberSeq.eq(member.memberSeq))
                .leftJoin(meeting).on(meetingMember.meeting.meetingSeq.eq(meeting.meetingSeq))
                .where(builder)
                .groupBy(member.memberName)
                .orderBy(member.memberName.count().desc())
                .limit(10)
                .fetch();
    }
}
