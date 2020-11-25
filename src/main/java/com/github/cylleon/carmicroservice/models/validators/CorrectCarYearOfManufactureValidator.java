package com.github.cylleon.carmicroservice.models.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Year;

public class CorrectCarYearOfManufactureValidator implements ConstraintValidator<CorrectCarYearOfManufacture, Integer> {
    @Override
    public boolean isValid(Integer carYearOfManufacture, ConstraintValidatorContext constraintValidatorContext) {
        return Year.now().getValue() - carYearOfManufacture < 15;
    }
}
