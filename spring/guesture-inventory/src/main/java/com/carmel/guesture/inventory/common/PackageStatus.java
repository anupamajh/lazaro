package com.carmel.guesture.inventory.common;

public enum PackageStatus {
    ACTIVE(1),
    DISABLED(2),
    PLANNED(3);

    private final int value;

    PackageStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
