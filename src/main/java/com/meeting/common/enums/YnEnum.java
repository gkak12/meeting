package com.meeting.common.enums;

import lombok.Getter;

@Getter
public enum YnEnum {

    TRUE(true, "true", 1, "true인 경우 표시합니다."),
    FALSE(false, "false", 0, "false인 경우 표시합니다.");

    private final Boolean boolVal;
    private final String strVal;
    private final Integer intVal;
    private final String desc;

    YnEnum(final Boolean boolVal, final String strVal, final Integer intVal, final String desc) {
        this.boolVal = boolVal;
        this.strVal = strVal;
        this.intVal = intVal;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isTrue() {
        return this.equals(TRUE);
    }

    public boolean isFalse() {
        return this.equals(FALSE);
    }
}
