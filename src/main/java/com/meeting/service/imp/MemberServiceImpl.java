package com.meeting.service.imp;

import com.meeting.domain.dto.MemberCreateDto;
import com.meeting.domain.mapper.MemberMapper;
import com.meeting.domain.vo.MemberVo;
import com.meeting.repository.MemberRepository;
import com.meeting.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Override
    public List<MemberVo> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toVo)
                .toList();
    }

    @Override
    public MemberVo findByMemberName(String name) {
        return memberMapper.toVo(
                memberRepository.findByMemberName(name)
        );
    }

    @Override
    public void createMember(MemberCreateDto memberCreateDto) {
        memberRepository.save(
                memberMapper.toCreateEntity(memberCreateDto)
        );
    }
}
