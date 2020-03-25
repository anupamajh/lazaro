package com.carmel.guestjini.inventory.response;



import com.carmel.guestjini.inventory.model.DTO.BookingDTO;

import java.util.ArrayList;
import java.util.List;

public class BookingResponse {
    private BookingDTO booking;
    private List<BookingDTO> bookingList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;
    private String inventoryId;

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = (booking);
    }

    public List<BookingDTO> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingDTO> bookingList) {
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
}
