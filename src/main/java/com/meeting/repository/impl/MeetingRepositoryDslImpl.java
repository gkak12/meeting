package com.meeting.repository.impl;

import com.meeting.common.ConditionBuilder;
import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.vo.MeetingContentVo;
import com.meeting.repository.MeetingRepositoryDsl;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.meeting.domain.entity.QContent.content;
import static com.meeting.domain.entity.QMeeting.meeting;

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
}
