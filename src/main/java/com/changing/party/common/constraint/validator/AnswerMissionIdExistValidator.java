package com.changing.party.common.constraint.validator;

import com.changing.party.common.constraint.annotation.ValidateAnswerMissionIdExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnswerMissionIdExistValidator implements ConstraintValidator<ValidateAnswerMissionIdExist, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return false;
    }
}
