package com.musalasoft.drones.interfaces;

import com.musalasoft.drones.util.Base64ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64ImageValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Image {
    String message() default "Invalid Base64-encoded image";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
