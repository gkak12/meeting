package com.meeting.repository;

import com.meeting.domain.entity.MeetingMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long>, MeetingMemberRepositoryDsl {
}
