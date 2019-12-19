package com.carmel.guestjini.accounts.common;

public enum AccountTicketStatus {
    ACTIVE(1),
    CANCELLED(2);

    private final int value;

    AccountTicketStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
