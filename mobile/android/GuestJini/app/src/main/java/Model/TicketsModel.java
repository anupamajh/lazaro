package Model;

import android.graphics.drawable.Drawable;

public class TicketsModel {
    String ticketsName;
    String ticketsAuthorName;
    String ticketsDate;
    String ticketsDescription;
    int profilePicture;



    int navigationIcon;

    public String getTicketsName() {
        return ticketsName;
    }

    public void setTicketsName(String ticketsName) {
        this.ticketsName = ticketsName;
    }

    public String getTicketsAuthorName() {
        return ticketsAuthorName;
    }

    public void setTicketsAuthorName(String ticketsAuthorName) {
        this.ticketsAuthorName = ticketsAuthorName;
    }

    public String getTicketsDate() {
        return ticketsDate;
    }

    public void setTicketsDate(String ticketsDate) {
        this.ticketsDate = ticketsDate;
    }

    public String getTicketsDescription() {
        return ticketsDescription;
    }

    public void setTicketsDescription(String ticketsDescription) {
        this.ticketsDescription = ticketsDescription;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getNavigationIcon() {
        return navigationIcon;
    }

    public void setNavigationIcon(int navigationIcon) {
        this.navigationIcon = navigationIcon;
    }
}
