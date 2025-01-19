package com.meeting.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CONTENT")
@NoArgsConstructor
public class Content {

    @Id
    @Column(name = "CONTENT_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentSeq;

    @Column(name = "CONTENT_NAME", nullable = false, length = 100)
    private String contentName;

    @Column(name = "CONTENT_CREATOR", length = 100)
    private String contentCreator;

    @Column(name = "CONTENT_RECOMMENDER", length = 20)
    private String contentRecommender;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "MEETING_SEQ")
    private Meeting meeting;
}
