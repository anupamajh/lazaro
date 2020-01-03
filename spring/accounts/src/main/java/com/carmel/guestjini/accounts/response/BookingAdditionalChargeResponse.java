package com.carmel.guestjini.accounts.response;

import com.carmel.guestjini.accounts.model.DTO.BookingAdditionalCharge;

import java.util.List;

public class BookingAdditionalChargeResponse {

    private BookingAdditionalCharge bookingAdditionalCharge;
    private List<BookingAdditionalCharge> bookingAdditionalChargeList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public BookingAdditionalCharge getBookingAdditionalCharge() {
        return bookingAdditionalCharge;
    }

    public void setBookingAdditionalCharge(BookingAdditionalCharge bookingAdditionalCharge) {
        this.bookingAdditionalCharge = bookingAdditionalCharge;
    }

    public List<BookingAdditionalCharge> getBookingAdditionalChargeList() {
        return bookingAdditionalChargeList;
    }

    public void setBookingAdditionalChargeList(List<BookingAdditionalCharge> bookingAdditionalChargeList) {
        this.bookingAdditionalChargeList = bookingAdditionalChargeList;
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
}
