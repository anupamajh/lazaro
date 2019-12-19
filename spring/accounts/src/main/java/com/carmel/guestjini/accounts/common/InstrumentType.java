package com.carmel.guestjini.accounts.common;

public enum InstrumentType {
    CASH(1),
    CHEQUE(2),
    ONLINE_BANK(3),
    OTHER(4);

    private final int value;

    InstrumentType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
