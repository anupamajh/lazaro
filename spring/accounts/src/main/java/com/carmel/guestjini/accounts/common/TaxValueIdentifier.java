package com.carmel.guestjini.accounts.common;

public enum TaxValueIdentifier {
    FIXED(1),
    PERCENTAGE(2);

    private final int value;

    TaxValueIdentifier(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
