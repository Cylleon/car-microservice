package com.github.cylleon.carmicroservice.models.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class CorrectCarPriceValidator implements ConstraintValidator<CorrectCarPrice, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal carPrice, ConstraintValidatorContext constraintValidatorContext) {
        return carPrice.compareTo(BigDecimal.ZERO) > 0;
    }
}
