package com.github.cylleon.carmicroservice.models.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectCarYearOfManufactureValidator.class)
public @interface CorrectCarYearOfManufacture {
    String message() default "Incorrect year of manufacture";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
