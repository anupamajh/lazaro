package com.carmel.guestjini.service.response.Booking;


import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.DTO.Booking.BookingDTO;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;

import java.util.ArrayList;
import java.util.List;

public class BookingResponse {
    private BookingDTO booking;
    private GuestDTO guest;
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

    public void setBooking(Booking booking) {
        this.booking = new BookingDTO(booking);
    }

    public List<BookingDTO> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = new ArrayList<>();
        bookingList.forEach(booking1 -> {
            this.bookingList.add(new BookingDTO(booking1));
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

    public GuestDTO getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = new GuestDTO(guest);
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }
}
