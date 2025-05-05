package com.meeting.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = YearQuarterValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD}) // 사용할 위치를 지정 (파라미터, 필드)
@Retention(RetentionPolicy.RUNTIME)                 // 런타임 동안 유지
public @interface ValidYearQuarter {

    // 기본 메시지
    String message() default "잘못된 분기 파라미터 입니다.";

    // 그룹 (선택적, 유효성 검사 시 사용)
    Class<?>[] groups() default {};

    // 추가 페이로드 (선택적)
    Class<? extends Payload>[] payload() default {};
}
