package com.changing.party.common.constraint.validator;

import com.changing.party.common.constraint.annotation.ValidateVerifyMissionImageOP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerifyMissionImageOPValidator implements ConstraintValidator<ValidateVerifyMissionImageOP, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("approve") || value.equals("reject");
    }
}
