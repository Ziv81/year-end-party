package com.changing.party.constraint.annotation;

import com.changing.party.constraint.validator.AnswerBinaryChooseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AnswerBinaryChooseValidator.class)
@Target({  FIELD})
@Retention(RUNTIME)
public @interface ValidateBinaryChoose {
    String message() default "Check list size and choose code";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
