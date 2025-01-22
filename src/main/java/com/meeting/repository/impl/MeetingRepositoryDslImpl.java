package com.meeting.repository.impl;

import com.meeting.common.ConditionBuilder;
import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.domain.vo.MeetingMemberVo;
import com.meeting.repository.MeetingRepositoryDsl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.meeting.domain.entity.QContent.content;
import static com.meeting.domain.entity.QMeeting.meeting;
import static com.meeting.domain.entity.QMeetingMember.meetingMember;
import static com.meeting.domain.entity.QMember.member;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MeetingRepositoryDslImpl implements MeetingRepositoryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Meeting> findMeetingsByMeetingDate(MeetingSearchDateDto meetingSearchDateDto) {
        return jpaQueryFactory
                .selectFrom(meeting)
                .where(
                        ConditionBuilder.buildDateBetween(
                                meeting.meetingDateTime,
                                meetingSearchDateDto.getStartDate(),
                                meetingSearchDateDto.getEndDate()
                        )
                )
                .fetch();
    }

    @Override
    public List<MeetingContentVo> findMeetingsByContentName(String contentName) {
        return jpaQueryFactory
                .select(Projections.fields(
                        MeetingContentVo.class,
                        content.contentName,
                        content.contentCreator,
                        content.contentRecommender,
                        meeting.meetingDateTime,
                        meeting.meetingPlace
                ))
                .from(meeting)
                .leftJoin(content)
                .on(meeting.meetingSeq.eq(content.meeting.meetingSeq))
                .where(content.contentName.like("%"+contentName+"%"))
                .fetch();
    }

    @Override
    public MeetingMemberVo findMinMaxMembersMeeting(MeetingSearchDateDto meetingSearchDateDto) {
        BooleanBuilder builder = new BooleanBuilder();
        builder
            .and(
                ConditionBuilder.buildDateBetween(
                    meeting.meetingDateTime,
                    meetingSearchDateDto.getStartDate(),
                    meetingSearchDateDto.getEndDate()
                )
            )
            .and(
                    meetingMember.isAttendance.eq(true)
            );

        JPAQuery<MeetingMemberVo> jpaQuery = jpaQueryFactory
                .select(Projections.fields(
                        MeetingMemberVo.class,
                        meeting.meetingSeq,
                        content.contentName,
                        meeting.meetingDateTime,
                        meeting.meetingPlace,
                        meetingMember.member.memberSeq.count().as("num")
                        )
                )
                .from(meeting)
                .leftJoin(meetingMember)
                .on(meeting.meetingSeq.eq(meetingMember.meeting.meetingSeq))
                .leftJoin(content)
                .on(meeting.meetingSeq.eq(content.meeting.meetingSeq))
                .where(builder)
                .groupBy(meeting.meetingSeq);

        if("Y".equals(meetingSearchDateDto.getMinMaxFlag())){
            jpaQuery
                    .orderBy(meetingMember.member.memberSeq.count().desc());
        } else{
            jpaQuery
                    .orderBy(meetingMember.member.memberSeq.count().asc());
        }

        MeetingMemberVo meetingMemberVo = jpaQuery.fetchFirst();

        builder = new BooleanBuilder();
        builder
            .and(
                meetingMember.meeting.meetingSeq.eq(meetingMemberVo.getMeetingSeq())
            )
            .and(
                meetingMember.isAttendance.eq(true)
            );

        List<MeetingMemberVo.Member> members = jpaQueryFactory
                .select(Projections.fields(
                        MeetingMemberVo.Member.class,
                        member.memberName
                ))
                .from(meetingMember)
                .leftJoin(member)
                .on(meetingMember.member.memberSeq.eq(member.memberSeq))
                .where(builder)
                .fetch();
        meetingMemberVo.setMembers(members);

        return meetingMemberVo;
    }
}
