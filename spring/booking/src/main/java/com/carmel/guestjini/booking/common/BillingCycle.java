package com.carmel.guestjini.booking.common;

public enum  BillingCycle {
    ONE_TIME(1),
    RENT_CYCLE(2);

    private final int value;

    BillingCycle(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
