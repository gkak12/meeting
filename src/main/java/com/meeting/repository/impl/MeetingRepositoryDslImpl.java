package com.meeting.repository.impl;

import com.meeting.common.ConditionBuilder;
import com.meeting.domain.dto.MeetingSearchDateDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.repository.MeetingRepositoryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                                meeting.meetingDate,
                                meetingSearchDateDto.getStartDate(),
                                meetingSearchDateDto.getEndDate()
                        )
                )
                .fetch();
    }
}
