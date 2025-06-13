package com.meeting.service.imp;

import com.meeting.common.enums.MeetingErrorEnums;
import com.meeting.common.exception.MeetingException;
import com.meeting.common.util.DateTimeUtil;
import com.meeting.domain.dto.request.RequestMemberCreateDto;
import com.meeting.domain.dto.request.RequestMemberUpdateDto;
import com.meeting.domain.entity.Meeting;
import com.meeting.domain.entity.Member;
import com.meeting.domain.mapper.MemberMapper;
import com.meeting.domain.dto.response.ResponseMemberMeetingVo;
import com.meeting.domain.dto.response.ResponseMemberVo;
import com.meeting.repository.MeetingMemberRepository;
import com.meeting.repository.MeetingRepository;
import com.meeting.repository.MemberRepository;
import com.meeting.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;

    private final DateTimeUtil dateTimeUtil;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMemberVo> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toVo)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseMemberVo findByMemberSeq(Long seq) {
        Member member = memberRepository.findById(seq).orElseThrow(() -> new MeetingException(MeetingErrorEnums.NOT_FOUND, "member is not found."));
        return memberMapper.toVo(member);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseMemberVo findByMemberName(String name) {
        Member member = Objects.requireNonNull(memberRepository.findByMemberName(name), "member is not found.");
        return memberMapper.toVo(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMemberMeetingVo> findLatestMeeingEachMember() {
        List<ResponseMemberMeetingVo> list = memberRepository.findLatestMeeingEachMember();
        List<Long> meetingSeqs = list.stream()
            .map(ResponseMemberMeetingVo::getMeetingSeq)
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
                item.setMeetingDateTime(dateTimeUtil.convertLocalDateTimeToString("yyyy-MM-dd HH:mm:ss", meeting.getMeetingDateTime()));
                item.setMeetingPlace(meeting.getMeetingPlace());
                item.setContentName(meeting.getContent().getContentName());
            });

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] downloadLatestMeeingEachMember() {
        StringBuilder sb = new StringBuilder();
        sb.append("name,date,place,content\n");

        List<ResponseMemberMeetingVo> list = memberRepository.findLatestMeeingEachMember();
        List<Long> meetingSeqs = list.stream()
            .map(ResponseMemberMeetingVo::getMeetingSeq)
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
                item.setMeetingDateTime(dateTimeUtil.convertLocalDateTimeToString("yyyy-MM-dd HH:mm:ss", meeting.getMeetingDateTime()));
                item.setMeetingPlace(meeting.getMeetingPlace());
                item.setContentName(meeting.getContent().getContentName());

                sb.append(item.getMemberName()).append(",")
                    .append(dateTimeUtil.convertLocalDateTimeToString("yyyy-MM-dd HH:mm:ss", meeting.getMeetingDateTime())).append(",")
                    .append(meeting.getMeetingPlace()).append(",")
                    .append(meeting.getContent().getContentName()).append("\n");
            });

        byte[] bom = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        byte[] data = sb.toString().getBytes(StandardCharsets.UTF_8);
        byte[] body = new byte[bom.length + data.length];
        System.arraycopy(bom, 0, body, 0, bom.length);
        System.arraycopy(data, 0, body, bom.length, data.length);

        return body;
    }

    @Override
    public List<ResponseMemberVo> findMeetingCountEachMember() {
        return memberRepository.findMeetingCountEachMember();
    }

    @Override
    @Transactional
    public void createMember(RequestMemberCreateDto requestMemberCreateDto) {
        memberRepository.save(
                memberMapper.toCreateEntity(requestMemberCreateDto)
        );
    }

    @Override
    @Transactional
    public void updateMember(RequestMemberUpdateDto requestMemberUpdateDto) {
        Long memberSeq = requestMemberUpdateDto.getMemberSeq();
        Member member = memberRepository.findById(memberSeq).orElseThrow(() -> new NullPointerException("member is not found."));
        memberMapper.toUpdateEntity(requestMemberUpdateDto, member);

        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void deleteMember(Long memberSeq) {
        Member member = memberRepository.findById(memberSeq).orElseThrow(() -> new NullPointerException("member is not found."));
        member.setIsDeleted(true);

        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMemberVo> findTop10AttendanceMemberByQuarter(String yearQuarter) {
        String[] part = yearQuarter.split("-");
        int year = Integer.parseInt(part[0]);
        int quarter = Integer.parseInt(part[1].replace("Q", ""));

        LocalDateTime startDateTime = dateTimeUtil.getQuarterStartDateTime(year, quarter);
        LocalDateTime endDateTime = dateTimeUtil.getQuarterEndDateTime(year, quarter);

        return meetingMemberRepository.findTop10AttendanceMemberByQuarter(startDateTime, endDateTime);
    }
}
