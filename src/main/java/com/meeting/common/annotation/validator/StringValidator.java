package com.meeting.common.annotation.validator;

import com.meeting.common.annotation.ValidString;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class StringValidator implements ConstraintValidator<ValidString, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotBlank(s);
    }
}
