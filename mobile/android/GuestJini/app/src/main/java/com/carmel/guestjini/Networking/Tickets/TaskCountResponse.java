package com.carmel.guestjini.Networking.Tickets;

public class TaskCountResponse {

    private int sharedInboxNewCount;
    private int sharedInboxUnassignedCount;
    private int sharedInboxOpenCount;
    private int sharedInboxClosedCount;
    private int yourInboxNewCount;
    private int yourInboxOpenCount;
    private int yourInboxClosedCount;
    private int teamInboxNewCount;
    private int teamInboxUnassignedCount;
    private int teamInboxOpenCount;
    private int teamInboxClosedCount;

    private boolean success;
    private String error;

    public int getSharedInboxNewCount() {
        return sharedInboxNewCount;
    }

    public void setSharedInboxNewCount(int sharedInboxNewCount) {
        this.sharedInboxNewCount = sharedInboxNewCount;
    }

    public int getSharedInboxUnassignedCount() {
        return sharedInboxUnassignedCount;
    }

    public void setSharedInboxUnassignedCount(int sharedInboxUnassignedCount) {
        this.sharedInboxUnassignedCount = sharedInboxUnassignedCount;
    }

    public int getSharedInboxOpenCount() {
        return sharedInboxOpenCount;
    }

    public void setSharedInboxOpenCount(int sharedInboxOpenCount) {
        this.sharedInboxOpenCount = sharedInboxOpenCount;
    }

    public int getSharedInboxClosedCount() {
        return sharedInboxClosedCount;
    }

    public void setSharedInboxClosedCount(int sharedInboxClosedCount) {
        this.sharedInboxClosedCount = sharedInboxClosedCount;
    }

    public int getYourInboxNewCount() {
        return yourInboxNewCount;
    }

    public void setYourInboxNewCount(int yourInboxNewCount) {
        this.yourInboxNewCount = yourInboxNewCount;
    }

    public int getYourInboxOpenCount() {
        return yourInboxOpenCount;
    }

    public void setYourInboxOpenCount(int yourInboxOpenCount) {
        this.yourInboxOpenCount = yourInboxOpenCount;
    }

    public int getYourInboxClosedCount() {
        return yourInboxClosedCount;
    }

    public void setYourInboxClosedCount(int yourInboxClosedCount) {
        this.yourInboxClosedCount = yourInboxClosedCount;
    }

    public int getTeamInboxNewCount() {
        return teamInboxNewCount;
    }

    public void setTeamInboxNewCount(int teamInboxNewCount) {
        this.teamInboxNewCount = teamInboxNewCount;
    }

    public int getTeamInboxUnassignedCount() {
        return teamInboxUnassignedCount;
    }

    public void setTeamInboxUnassignedCount(int teamInboxUnassignedCount) {
        this.teamInboxUnassignedCount = teamInboxUnassignedCount;
    }

    public int getTeamInboxOpenCount() {
        return teamInboxOpenCount;
    }

    public void setTeamInboxOpenCount(int teamInboxOpenCount) {
        this.teamInboxOpenCount = teamInboxOpenCount;
    }

    public int getTeamInboxClosedCount() {
        return teamInboxClosedCount;
    }

    public void setTeamInboxClosedCount(int teamInboxClosedCount) {
        this.teamInboxClosedCount = teamInboxClosedCount;
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
