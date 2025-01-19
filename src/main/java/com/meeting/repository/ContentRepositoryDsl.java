package com.meeting.repository;

import com.meeting.domain.entity.Content;

import java.util.List;

public interface ContentRepositoryDsl {
    List<Content> findContentByContentName(String contentName);
}
