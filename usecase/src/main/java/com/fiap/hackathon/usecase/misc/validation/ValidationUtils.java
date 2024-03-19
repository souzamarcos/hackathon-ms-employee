package com.fiap.hackathon.usecase.misc.validation;

import com.fiap.hackathon.usecase.misc.exception.BlankAttributeException;
import com.fiap.hackathon.usecase.misc.exception.InvalidEmailFormatException;
import com.fiap.hackathon.usecase.misc.exception.NegativeOrZeroValueException;
import com.fiap.hackathon.usecase.misc.exception.NullAttributeException;

import java.util.regex.Pattern;

public class ValidationUtils {
    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateNotNull(Object value, String attributeName) {
        if (value == null) throw new NullAttributeException(attributeName);
    }

    public static void validateNotBlank(String value, String attributeName) {
        if (value.isBlank()) throw new BlankAttributeException(attributeName);
    }

    public static void validatePositiveNotZero(Double value, String attributeName) {
        if (value <= 0) throw new NegativeOrZeroValueException(attributeName);
    }

    public static void validateEmailFormat(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new InvalidEmailFormatException();
        }
    }
}
