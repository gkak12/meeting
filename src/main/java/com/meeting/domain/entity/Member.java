package com.meeting.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = "meetingMembers")
@Entity
@Table(name = "MEMBER", schema = "MEETING")
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "MEMBER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;

    @Column(name = "MEMBER_NAME", nullable = false, length = 20)
    private String memberName;

    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted;

    @JsonManagedReference
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MeetingMember> meetingMembers;
}
