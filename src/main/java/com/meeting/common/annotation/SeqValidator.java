package com.meeting.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SeqValidator implements ConstraintValidator<ValidSeq, Long> {

    @Override
    public boolean isValid(Long seq, ConstraintValidatorContext context) {
        return seq != null && seq > 0;
    }
}
