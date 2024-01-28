package com.musalasoft.drones.util;

import com.musalasoft.drones.interfaces.Base64Image;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class Base64ImageValidator implements ConstraintValidator<Base64Image, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String imageData = value.replaceAll("^data:image/[a-zA-Z]+;base64,", "");

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(imageData);
            String mimeType = detectMimeType(decodedBytes);
            return mimeType.startsWith("data:image/");
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private String detectMimeType(byte[] bytes) {
        return "data:image/";
    }
}


