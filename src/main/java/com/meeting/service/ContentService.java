package com.meeting.service;

import com.meeting.domain.dto.response.ResponseContentVo;

import java.util.List;

public interface ContentService {

    List<ResponseContentVo> findAllContents();
    List<ResponseContentVo> findContentByContentName(String contentName);
    ResponseContentVo findMostSelectedContent();
}
