package com.meeting.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponsePageVo {

    private int totalPages;     // 전체 페이지 수
    private long totalItems;    // 전체 항목 수
}

