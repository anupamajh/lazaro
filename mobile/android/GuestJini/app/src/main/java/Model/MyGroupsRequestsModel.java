package Model;

public class MyGroupsRequestsModel {
    private int requestsProfilePicture;
    private String requestsMembersName;
    private String requestsDate;
    private int requestStatusIcon;
    private String requestsStatus;

    public MyGroupsRequestsModel(int requestsProfilePicture, String requestsMembersName, String requestsDate, int requestStatusIcon, String requestsStatus) {
        this.requestsProfilePicture = requestsProfilePicture;
        this.requestsMembersName = requestsMembersName;
        this.requestsDate = requestsDate;
        this.requestStatusIcon = requestStatusIcon;
        this.requestsStatus = requestsStatus;
    }

    public int getRequestsProfilePicture() {
        return requestsProfilePicture;
    }

    public void setRequestsProfilePicture(int requestsProfilePicture) {
        this.requestsProfilePicture = requestsProfilePicture;
    }

    public String getRequestsMembersName() {
        return requestsMembersName;
    }

    public void setRequestsMembersName(String requestsMembersName) {
        this.requestsMembersName = requestsMembersName;
    }

    public String getRequestsDate() {
        return requestsDate;
    }

    public void setRequestsDate(String requestsDate) {
        this.requestsDate = requestsDate;
    }

    public int getRequestStatusIcon() {
        return requestStatusIcon;
    }

    public void setRequestStatusIcon(int requestStatusIcon) {
        this.requestStatusIcon = requestStatusIcon;
    }

    public String getRequestsStatus() {
        return requestsStatus;
    }

    public void setRequestsStatus(String requestsStatus) {
        this.requestsStatus = requestsStatus;
    }
}
