package com.meeting.common.exception;

import com.meeting.common.enums.MeetingErrorEnums;
import lombok.Getter;

@Getter
public class MeetingException extends RuntimeException {

    private int statusCode;
    private String message;

    public MeetingException() {
        this.statusCode = MeetingErrorEnums.NOT_FOUND.getCode();
        this.message = MeetingErrorEnums.NOT_FOUND.getMsg();
    }

    public MeetingException(MeetingErrorEnums error) {
        this.statusCode = error.getCode();
        this.message = error.getMsg();
    }

    public MeetingException(MeetingErrorEnums error, String message) {
        this.statusCode = error.getCode();
        this.message = message;
    }
}
