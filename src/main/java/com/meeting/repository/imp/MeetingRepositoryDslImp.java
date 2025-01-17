package com.meeting.repository.imp;

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
public class MeetingRepositoryDslImp implements MeetingRepositoryDsl {
}
