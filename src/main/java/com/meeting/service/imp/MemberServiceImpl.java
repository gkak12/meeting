package com.meeting.service.imp;

import com.meeting.common.util.DateTimeUtil;
import com.meeting.domain.dto.MemberCreateDto;
import com.meeting.domain.dto.MemberUpdateDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.entity.Member;
import com.meeting.domain.mapper.MemberMapper;
import com.meeting.domain.vo.MemberMeetingVo;
import com.meeting.domain.vo.MemberVo;
import com.meeting.repository.MeetingRepository;
import com.meeting.repository.MemberRepository;
import com.meeting.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;

    @Override
    public List<MemberVo> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toVo)
                .toList();
    }

    @Override
    public MemberVo findByMemberName(String name) {
        Member member = Objects.requireNonNull(memberRepository.findByMemberName(name), "member is not found.");
        return memberMapper.toVo(member);
    }

    @Override
    public List<MemberMeetingVo> findLatestMeeingEachMember() {
        List<MemberMeetingVo> list = memberRepository.findLatestMeeingEachMember();
        List<Long> meetingSeqs = list.stream()
            .map(MemberMeetingVo::getMeetingSeq)
            .collect(Collectors.toList());

        Map<Long, Meeting> map = meetingRepository.findMeetingContent(meetingSeqs).stream()
            .collect(Collectors.toMap(
                Meeting::getMeetingSeq,
                meeting -> meeting,
                (existing, replacement) -> existing
            ));

        list.stream()
            .forEach(item -> {
                Long meetingSeq = item.getMeetingSeq();
                Meeting meeting = map.get(meetingSeq);
                item.setMeetingDateTime(DateTimeUtil.convertLocalDateTimeToString("yyyy-MM-dd HH:mm:ss", meeting.getMeetingDateTime()));
                item.setMeetingPlace(meeting.getMeetingPlace());
                item.setContentName(meeting.getContent().getContentName());
            });

        return list;
    }

    @Override
    public void createMember(MemberCreateDto memberCreateDto) {
        memberRepository.save(
                memberMapper.toCreateEntity(memberCreateDto)
        );
    }

    @Override
    public void updateMember(MemberUpdateDto memberUpdateDto) {
        Long memberSeq = memberUpdateDto.getMemberSeq();
        Member member = memberRepository.findById(memberSeq).orElseThrow(() -> new NullPointerException("member is not found."));
        memberMapper.toUpdateEntity(memberUpdateDto, member);

        memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long memberSeq) {
        Member member = memberRepository.findById(memberSeq).orElseThrow(() -> new NullPointerException("member is not found."));
        member.setIsDeleted(true);

        memberRepository.save(member);
    }
}
