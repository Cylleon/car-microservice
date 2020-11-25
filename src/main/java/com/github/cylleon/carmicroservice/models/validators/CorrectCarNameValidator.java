package com.github.cylleon.carmicroservice.models.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectCarNameValidator implements ConstraintValidator<CorrectCarName, String> {

    @Override
    public boolean isValid(String carName, ConstraintValidatorContext constraintValidatorContext) {
        return carName.matches("^[_A-z0-9]*$");
    }
}
