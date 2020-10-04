package com.entra.core.basqueteapi.util;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static void validateInteger(Integer number, String messageError) {
        if (number == null) {
            throw new IllegalArgumentException(messageError);
        }
    }
}
