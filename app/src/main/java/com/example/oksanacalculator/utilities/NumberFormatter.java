package com.example.oksanacalculator.utilities;

public class NumberFormatter {

    private static final char SEPARATOR = '.';

    public static String removeTrailingZeros(double number) {
        StringBuilder stringRepresentation = new StringBuilder(String.valueOf(number));
        int index = stringRepresentation.length() - 1;
        while (stringRepresentation.charAt(index) != SEPARATOR
                && stringRepresentation.charAt(index) == '0' ) {
            stringRepresentation.deleteCharAt(index);
            index -= 1;
        }
        int lastCharIndex = stringRepresentation.length() - 1;
        if (stringRepresentation.charAt(lastCharIndex) == SEPARATOR) {
            stringRepresentation.deleteCharAt(lastCharIndex);
        }
        return stringRepresentation.toString();
    }
}

