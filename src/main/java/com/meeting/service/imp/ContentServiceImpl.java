package com.meeting.service.imp;

import com.meeting.common.enums.MeetingErrorEnums;
import com.meeting.common.exception.MeetingException;
import com.meeting.domain.dto.response.ResponseContentVo;
import com.meeting.domain.mapper.ContentMapper;
import com.meeting.repository.ContentRepository;
import com.meeting.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentServiceImpl implements ContentService {

    private final ContentMapper contentMapper;
    private final ContentRepository contentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseContentVo> findAllContents() {
        return contentRepository.findAll().stream()
                .map(contentMapper::toVo)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseContentVo> findContentByContentName(String contentName) {
        return contentRepository.findContentByContentName(contentName).stream()
                .map(contentMapper::toVo)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseContentVo findMostSelectedContent() {
        ResponseContentVo responseContentVo = contentRepository.findMostSelectedContent();

        if(responseContentVo.getSelectionNumber() == 1L){
            throw new MeetingException(MeetingErrorEnums.INTERNAL_SERVER_ERROR, "작품 최다 선정 수가 1입니다.");
        }

        return responseContentVo;
    }
}
