package com.carmel.guestjini.booking.response;

import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import com.carmel.guestjini.booking.model.DTO.BookingAdditionalChargeDTO;

import java.util.ArrayList;
import java.util.List;

public class BookingAdditionalChargeResponse {
    private BookingAdditionalChargeDTO bookingAdditionalCharge;
    private List<BookingAdditionalChargeDTO> bookingAdditionalChargeList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public BookingAdditionalChargeDTO getBookingAdditionalCharge() {
        return bookingAdditionalCharge;
    }

    public void setBookingAdditionalCharge(BookingAdditionalCharge bookingAdditionalCharge) {
        this.bookingAdditionalCharge = new BookingAdditionalChargeDTO( bookingAdditionalCharge);
    }

    public List<BookingAdditionalChargeDTO> getBookingAdditionalChargeList() {
        return bookingAdditionalChargeList;
    }

    public void setBookingAdditionalChargeList(List<BookingAdditionalCharge> bookingAdditionalChargeList) {
        this.bookingAdditionalChargeList = new ArrayList<>();
        bookingAdditionalChargeList.forEach(bookingAdditionalCharge1 -> {
            this.bookingAdditionalChargeList.add(new BookingAdditionalChargeDTO(bookingAdditionalCharge1));
        });
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
