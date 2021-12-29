package com.changing.party.common.constraint.validator;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.constraint.annotation.ValidateBinaryChoose;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AnswerBinaryChooseValidator implements ConstraintValidator<ValidateBinaryChoose, List<Integer>> {
    @Override
    public boolean isValid(List<Integer> value, ConstraintValidatorContext context) {
        if (value.size() != GlobalVariable.getGlobalVariable().getBINARY_QUESTION_LIST().size())
            return false;
        return !value.stream().anyMatch(x -> x < 1 || x > 3);
    }
}
