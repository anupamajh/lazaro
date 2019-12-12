package com.carmel.guestjini.inventory.common;

public enum RentCycle {
    PER_DAY(1),
    PER_MONTH(2);

    private final int value;

    RentCycle(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
