package com.carmel.guestjini.Common;

public class StringUtils {
    public static final String ELLIPSE = "...";
    public static String toEllipsis(String input, int maxCharacters, int charactersAfterEllipse) {
        if (input == null || input.length() < maxCharacters) {
            return input;
        }
        return input.substring(0, maxCharacters - charactersAfterEllipse)
                + ELLIPSE
                + input.substring(input.length() - charactersAfterEllipse);
    }

    private StringUtils() { }
}
