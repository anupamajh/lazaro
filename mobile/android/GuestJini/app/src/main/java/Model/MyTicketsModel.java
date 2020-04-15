package Model;

public class MyTicketsModel {
    private String ticketsStatus;
    private String ticketsDateAndTime;
    private String ticketsName;
    private String ticketsNo;
    private String ticketsValue;
    private String clock;
    private String ticketsTime;
    private int notificationIcon;
    private String button;
    private String delete;
    private int viewType;
    public static final int ONE_TYPE = 1;
    public static final int TWO_TYPE = 2;

    public MyTicketsModel(String ticketsStatus, String ticketsDateAndTime, String ticketsName, String ticketsNo, String ticketsValue, String clock, String ticketsTime,int notificationIcon, String delete, int viewType) {
        this.ticketsStatus = ticketsStatus;
        this.ticketsDateAndTime = ticketsDateAndTime;
        this.ticketsName = ticketsName;
        this.ticketsNo = ticketsNo;
        this.ticketsValue = ticketsValue;
        this.clock = clock;
        this.ticketsTime = ticketsTime;
        this.notificationIcon=notificationIcon;
        this.delete = delete;
        this.viewType = viewType;

    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getTicketsStatus() {
        return ticketsStatus;
    }

    public void setTicketsStatus(String ticketsStatus) {
        this.ticketsStatus = ticketsStatus;
    }

    public String getTicketsDateAndTime() {
        return ticketsDateAndTime;
    }

    public void setTicketsDateAndTime(String ticketsDateAndTime) {
        this.ticketsDateAndTime = ticketsDateAndTime;
    }

    public String getTicketsName() {
        return ticketsName;
    }

    public void setTicketsName(String ticketsName) {
        this.ticketsName = ticketsName;
    }

    public String getTicketsNo() {
        return ticketsNo;
    }

    public void setTicketsNo(String ticketsNo) {
        this.ticketsNo = ticketsNo;
    }

    public String getTicketsValue() {
        return ticketsValue;
    }

    public void setTicketsValue(String ticketsValue) {
        this.ticketsValue = ticketsValue;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getTicketsTime() {
        return ticketsTime;
    }

    public void setTicketsTime(String ticketsTime) {
        this.ticketsTime = ticketsTime;
    }

    public int getNotificationIcon() {
        return notificationIcon;
    }

    public void setNotificationIcon(int notificationIcon) {
        this.notificationIcon = notificationIcon;
    }

}