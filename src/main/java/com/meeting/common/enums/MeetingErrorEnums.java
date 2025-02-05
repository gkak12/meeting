package com.meeting.common.enums;

import lombok.Getter;

@Getter
public enum MeetingErrorEnums {

    BAD_REQUEST(400, "잘못된 요청입니다."),
    UNAUTHENTICATED(401, "인증되지 않은 사용자 요청입니다."),
    PERMISSION_DENIED(403, "권한이 없는 사용자 요청입니다."),
    NOT_FOUND(404, "해당 요청은 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부에서 에러가 발생했습니다.");

    private final int code;
    private final String msg;

    MeetingErrorEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
