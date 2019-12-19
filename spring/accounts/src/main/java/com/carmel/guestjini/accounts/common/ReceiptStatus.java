package com.carmel.guestjini.accounts.common;

public enum ReceiptStatus {
    ACTIVE(1),
    CANCELLED(2);

    private final int value;

    ReceiptStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
