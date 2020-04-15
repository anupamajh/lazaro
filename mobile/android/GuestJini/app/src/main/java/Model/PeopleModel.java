package Model;

public class PeopleModel {
    private int profilePicture;
    private String addPeopleName;
    private String addPeopleGender;
    private int notificationIndicator;
    private int favouritesIcon;
    private String compatibilityCount;


    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAddPeopleName() {
        return addPeopleName;
    }

    public void setAddPeopleName(String addPeopleName) {
        this.addPeopleName = addPeopleName;
    }

    public String getAddPeopleGender() {
        return addPeopleGender;
    }

    public void setAddPeopleGender(String addPeopleGender) {
        this.addPeopleGender = addPeopleGender;
    }

    public int getNotificationIndicator() {
        return notificationIndicator;
    }

    public void setNotificationIndicator(int notificationIndicator) {
        this.notificationIndicator = notificationIndicator;
    }

    public int getFavouritesIcon() {
        return favouritesIcon;
    }

    public void setFavouritesIcon(int favouritesIcon) {
        this.favouritesIcon = favouritesIcon;
    }

    public String getCompatibilityCount() {
        return compatibilityCount;
    }

    public void setCompatibilityCount(String  compatibilityCount) {
        this.compatibilityCount = compatibilityCount;
    }
}
