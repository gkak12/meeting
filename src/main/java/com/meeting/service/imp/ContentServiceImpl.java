package com.meeting.service.imp;

import com.meeting.domain.mapper.ContentMapper;
import com.meeting.domain.vo.ContentVo;
import com.meeting.repository.ContentRepository;
import com.meeting.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentMapper contentMapper;
    private final ContentRepository contentRepository;

    @Override
    public List<ContentVo> findAllContents() {
        return contentRepository.findAll().stream()
                .map(contentMapper::toVo)
                .toList();
    }

    @Override
    public List<ContentVo> findContentByContentName(String contentName) {
        return contentRepository.findContentByContentName(contentName).stream()
                .map(contentMapper::toVo)
                .toList();
    }

    @Override
    public ContentVo findMostSelectedContent() {
        return contentRepository.findMostSelectedContent();
    }
}
