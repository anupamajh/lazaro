package com.carmel.guestjini.booking.response;

import com.carmel.guestjini.booking.model.BookingSource;

import java.util.List;

public class BookingSourceResponse {
    private BookingSource bookingSource;
    private List<BookingSource> bookingSourceList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public BookingSource getBookingSource() {
        return bookingSource;
    }

    public void setBookingSource(BookingSource bookingSource) {
        this.bookingSource = bookingSource;
    }

    public List<BookingSource> getBookingSourceList() {
        return bookingSourceList;
    }

    public void setBookingSourceList(List<BookingSource> bookingSourceList) {
        this.bookingSourceList = bookingSourceList;
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
