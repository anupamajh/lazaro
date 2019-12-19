package com.carmel.guestjini.accounts.common;

public enum  AccountTicketIdentifier {
    RENT_INVOICE(1),
    BILL(2),
    DEBIT_NOTE(3),
    CREDIT_NOTE(4);

    private final int value;

    AccountTicketIdentifier(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
