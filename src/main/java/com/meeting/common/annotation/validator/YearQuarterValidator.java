package com.meeting.common.annotation.validator;

import com.meeting.common.annotation.ValidYearQuarter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearQuarterValidator implements ConstraintValidator<ValidYearQuarter, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isBlank() || !s.contains("-") || !s.endsWith("Q")){
            return false;
        }

        String[] part = s.split("-");

        if(part.length != 2){
            return false;
        }

        if(part[0].length() != 4 || !part[0].chars().allMatch(Character::isDigit)){
            return false;
        }

        if(part[1].length() != 2 || !Character.isDigit(part[1].charAt(0)) || !(part[1].charAt(0) >= '1' && part[1].charAt(0) <= '4') || part[1].charAt(1) != 'Q'){
            return false;
        }

        return true;
    }
}
