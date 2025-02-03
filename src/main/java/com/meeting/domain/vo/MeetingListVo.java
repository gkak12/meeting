package com.meeting.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MeetingListVo {

    private List<MeetingVo> list;
    private PageVo page;
}
