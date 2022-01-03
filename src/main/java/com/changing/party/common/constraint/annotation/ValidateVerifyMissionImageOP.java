package com.changing.party.common.constraint.annotation;

import com.changing.party.common.constraint.validator.VerifyMissionImageOPValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = VerifyMissionImageOPValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidateVerifyMissionImageOP {
    String message() default "OP not accept.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
