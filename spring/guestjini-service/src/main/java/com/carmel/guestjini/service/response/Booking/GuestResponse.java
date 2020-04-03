package com.carmel.guestjini.service.response.Booking;


import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.DTO.Booking.GuestDTO;

import java.util.ArrayList;
import java.util.List;

public class GuestResponse {
    private GuestDTO guest;
    private List<GuestDTO> guestList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public GuestDTO getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = new GuestDTO(guest);
    }

    public List<GuestDTO> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = new ArrayList<>();
        guestList.forEach(guest1 -> {
            this.guestList.add(new GuestDTO(guest1));
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
