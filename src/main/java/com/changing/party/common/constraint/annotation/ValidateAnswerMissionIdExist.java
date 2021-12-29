package com.changing.party.common.constraint.annotation;

import com.changing.party.common.constraint.validator.AnswerMissionIdExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AnswerMissionIdExistValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidateAnswerMissionIdExist {
    String message() default "Mission id not mapping";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
