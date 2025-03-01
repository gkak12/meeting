package com.meeting.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "MEETING_MEMBER", schema = "MEETING")
@NoArgsConstructor
public class MeetingMember {

    @Id
    @Column(name = "MEETING_MEMBER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingMemberSeq;

    @Column(name = "IS_ATTENDANCE")
    private Boolean isAttendance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEETING_SEQ")
    @JsonBackReference
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ")
    @JsonBackReference
    private Member member;
}
