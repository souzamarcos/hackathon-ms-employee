package com.fiap.hackathon.usecase.misc.validation;


import com.fiap.hackathon.usecase.misc.exception.InvalidCPFException;

public class ValidationCPF {
    private ValidationCPF() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateCPF(String cpf) throws InvalidCPFException {
        if (containsNonNumericCharacters(cpf) || isInvalid(cpf))
            throw new InvalidCPFException();
    }

    private static boolean containsNonNumericCharacters(String cpf) {
        return cpf.matches(".*\\D.*");
    }

    private static boolean isInvalid(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || hasSameDigit(cpf))
            return true;

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        return !validateDigits(digits);
    }

    private static boolean hasSameDigit(String cpf) {
        char firstDigit = cpf.charAt(0);
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != firstDigit)
                return false;
        }
        return true;
    }

    private static boolean validateDigits(int[] digits) {
        int sum = 0;
        int weight = 10;

        for (int i = 0; i < 9; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        int firstDigitVerifier = 11 - (sum % 11);
        if (firstDigitVerifier >= 10)
            firstDigitVerifier = 0;

        sum = 0;
        weight = 11;

        for (int i = 0; i < 10; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        int secondDigitVerifier = 11 - (sum % 11);
        if (secondDigitVerifier >= 10)
            secondDigitVerifier = 0;

        return digits[9] == firstDigitVerifier && digits[10] == secondDigitVerifier;
    }
}
