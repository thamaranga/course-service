package com.hasithat.courseservice.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CourseTypeValidator implements ConstraintValidator<CourseTypeValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List list = Arrays.asList("LIVE", "RECORDING");
        return list.contains(s);
    }
}
