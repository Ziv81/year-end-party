package com.changing.party.common.constraint.validator;

import com.changing.party.common.constraint.annotation.ValidateAnswerMissionIdExist;
import com.changing.party.common.constraint.annotation.ValidateAnswerMissionType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnswerMissionTypeValidator implements ConstraintValidator<ValidateAnswerMissionType, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return false;
    }
}
