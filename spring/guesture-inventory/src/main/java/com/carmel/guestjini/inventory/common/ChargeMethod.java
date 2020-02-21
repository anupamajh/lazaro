package com.carmel.guestjini.inventory.common;

public enum ChargeMethod {
    INCLUDE_IN_RENT_INVOICE(1),
    CREATE_DEBIT_NOTE(2),
    CREATE_BILL(3);

    private final int value;

    ChargeMethod(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
