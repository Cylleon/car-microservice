package com.github.cylleon.carmicroservice.models.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectCarNameValidator.class)
public @interface CorrectCarName {
    String message() default "Incorrect name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
