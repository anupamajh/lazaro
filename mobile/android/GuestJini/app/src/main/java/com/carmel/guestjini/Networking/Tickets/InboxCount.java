package com.carmel.guestjini.Networking.Tickets;

public class InboxCount {
    private int sharedUnAssigned;
    private int sharedOpen;
    private int sharedClosed;
    private int myOpen;
    private int myClosed;
    private int teamUnassigned;
    private int teamOpen;
    private int teamClosed;
    private boolean isGroupAdmin;
    private boolean success;
    private String error;

    public int getSharedUnAssigned() {
        return sharedUnAssigned;
    }

    public void setSharedUnAssigned(int sharedUnAssigned) {
        this.sharedUnAssigned = sharedUnAssigned;
    }

    public int getSharedOpen() {
        return sharedOpen;
    }

    public void setSharedOpen(int sharedOpen) {
        this.sharedOpen = sharedOpen;
    }

    public int getSharedClosed() {
        return sharedClosed;
    }

    public void setSharedClosed(int sharedClosed) {
        this.sharedClosed = sharedClosed;
    }

    public int getMyOpen() {
        return myOpen;
    }

    public void setMyOpen(int myOpen) {
        this.myOpen = myOpen;
    }

    public int getMyClosed() {
        return myClosed;
    }

    public void setMyClosed(int myClosed) {
        this.myClosed = myClosed;
    }

    public int getTeamUnassigned() {
        return teamUnassigned;
    }

    public void setTeamUnassigned(int teamUnassigned) {
        this.teamUnassigned = teamUnassigned;
    }

    public int getTeamOpen() {
        return teamOpen;
    }

    public void setTeamOpen(int teamOpen) {
        this.teamOpen = teamOpen;
    }

    public int getTeamClosed() {
        return teamClosed;
    }

    public void setTeamClosed(int teamClosed) {
        this.teamClosed = teamClosed;
    }

    public boolean isGroupAdmin() {
        return isGroupAdmin;
    }

    public void setGroupAdmin(boolean groupAdmin) {
        isGroupAdmin = groupAdmin;
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
