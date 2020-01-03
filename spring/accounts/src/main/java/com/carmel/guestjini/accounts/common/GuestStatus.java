package com.carmel.guestjini.accounts.common;

public enum GuestStatus {

    RESIDING(1),
    CHECKED_OUT(2),
    USED(3);

    private final int value;

    GuestStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
