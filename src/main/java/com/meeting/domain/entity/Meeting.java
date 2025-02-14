package com.meeting.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "MEETING", schema = "MEETING")
@NoArgsConstructor
public class Meeting {

    @Id
    @Column(name = "MEETING_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingSeq;

    @Column(name = "MEETING_DATE_TIME")
    private LocalDateTime meetingDateTime;

    @Column(name = "MEETING_PLACE", length = 50)
    private String meetingPlace;

    @JsonManagedReference
    @OneToOne(mappedBy = "meeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Content content;

    @JsonManagedReference
    @OneToMany(mappedBy = "meeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MeetingMember> meetingMembers;
}
