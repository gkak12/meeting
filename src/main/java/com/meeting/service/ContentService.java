package com.meeting.service;

import com.meeting.domain.vo.ContentVo;

import java.util.List;

public interface ContentService {

    List<ContentVo> findAllContents();
    List<ContentVo> findContentByContentName(String contentName);
    ContentVo findMostSelectedContent();
}
