package com.meeting.common.enums;

import lombok.Getter;

@Getter
public enum YnEnum {

    TRUE(true, "true", "Y", 1, "참인 경우 표시합니다."),
    FALSE(false, "false", "N", 0, "거짓인 경우 표시합니다.");

    private final Boolean boolVal;
    private final String strVal;
    private final String ynVal;
    private final Integer intVal;
    private final String desc;

    YnEnum(Boolean boolVal, String strVal, String ynVal, Integer intVal, String desc) {
        this.boolVal = boolVal;
        this.strVal = strVal;
        this.ynVal = ynVal;
        this.intVal = intVal;
        this.desc = desc;
    }

    public boolean isTrue() {
        return this.equals(TRUE);
    }

    public boolean isFalse() {
        return this.equals(FALSE);
    }
}
