package com.carmel.guestjini.Networking.OTP;

public class OTPResponse {
    private String Status;
    private String Details;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}