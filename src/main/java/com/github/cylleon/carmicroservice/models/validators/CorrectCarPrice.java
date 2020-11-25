package com.github.cylleon.carmicroservice.models.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectCarPriceValidator.class)
public @interface CorrectCarPrice {
    String message() default "Incorrect price";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
