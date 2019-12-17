package com.carmel.guestjini.booking.common;

public enum BookingStatus {
    ACTIVE(1),
    CANCELLED(2),
    USED(3);

    private final int value;

    BookingStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
