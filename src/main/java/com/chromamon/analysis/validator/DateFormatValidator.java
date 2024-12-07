package com.chromamon.analysis.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements ConstraintValidator<com.chromamon.analysis.validator.DateFormat, String> {

    private String pattern;

    @Override
    public void initialize(com.chromamon.analysis.validator.DateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
