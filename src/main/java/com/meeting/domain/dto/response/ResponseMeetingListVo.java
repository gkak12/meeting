package com.meeting.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseMeetingListVo {

    private List<ResponseMeetingVo> list;
    private ResponsePageVo page;
}
