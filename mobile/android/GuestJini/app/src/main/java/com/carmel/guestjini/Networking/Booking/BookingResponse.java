package com.carmel.guestjini.Networking.Booking;

import java.util.List;

public class BookingResponse {
    private Booking booking;
    private List<Booking> bookingList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private boolean hasBooking;
    private boolean hasUser;
    private String error;
    private String inventoryId;
    private boolean isCustomer;
    private boolean isSupportTeamMember;
    private boolean isResiding;
    private boolean hasSupportAccount;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
        this.currentRecords = currentRecords;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public boolean isHasBooking() {
        return hasBooking;
    }

    public void setHasBooking(boolean hasBooking) {
        this.hasBooking = hasBooking;
    }

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    public boolean isSupportTeamMember() {
        return isSupportTeamMember;
    }

    public void setSupportTeamMember(boolean supportTeamMember) {
        isSupportTeamMember = supportTeamMember;
    }

    public boolean isResiding() {
        return isResiding;
    }

    public void setResiding(boolean residing) {
        isResiding = residing;
    }

    public boolean isHasSupportAccount() {
        return hasSupportAccount;
    }

    public void setHasSupportAccount(boolean hasSupportAccount) {
        this.hasSupportAccount = hasSupportAccount;
    }
}
