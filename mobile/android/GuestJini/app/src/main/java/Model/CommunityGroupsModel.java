package Model;

import java.io.Serializable;

public class CommunityGroupsModel implements Serializable {

    private String communityGroupAdmin;
    private String communityGroupCreationDateAndTime;
    private String communityGroupDescription;
    private String communityGroupInformationMessage;
    private int adminProfileIcon;
    private int informationIcon;
    private int viewType;
    public static final int InvitedUnreadUserCell = 1;
    public static final int RequestAcceptedCell = 2;
    public static final int JoinedGroupsCell = 3;
    public static final int RemovedCell = 4;
    public static final int RequestedCell = 5;
    public static final int UnsubscribedCell = 6;
    public static final int InvitedReadCell = 7;


    public CommunityGroupsModel(String communityGroupTitle, String communityGroupAdmin, String communityGroupCreationDateAndTime, String communityGroupDescription, String communityGroupInformationMessage, int adminProfileIcon, int informationIcon, int viewType) {
        this.communityGroupTitle = communityGroupTitle;
        this.communityGroupAdmin = communityGroupAdmin;
        this.communityGroupCreationDateAndTime = communityGroupCreationDateAndTime;
        this.communityGroupDescription = communityGroupDescription;
        this.communityGroupInformationMessage = communityGroupInformationMessage;
        this.adminProfileIcon = adminProfileIcon;
        this.informationIcon = informationIcon;
        this.viewType = viewType;
    }

    private String communityGroupTitle;

    public String getCommunityGroupTitle() {
        return communityGroupTitle;
    }

    public void setCommunityGroupTitle(String communityGroupTitle) {
        this.communityGroupTitle = communityGroupTitle;
    }

    public String getCommunityGroupAdmin() {
        return communityGroupAdmin;
    }

    public void setCommunityGroupAdmin(String communityGroupAdmin) {
        this.communityGroupAdmin = communityGroupAdmin;
    }

    public String getCommunityGroupCreationDateAndTime() {
        return communityGroupCreationDateAndTime;
    }

    public void setCommunityGroupCreationDateAndTime(String communityGroupCreationDateAndTime) {
        this.communityGroupCreationDateAndTime = communityGroupCreationDateAndTime;
    }

    public String getCommunityGroupDescription() {
        return communityGroupDescription;
    }

    public void setCommunityGroupDescription(String communityGroupDescription) {
        this.communityGroupDescription = communityGroupDescription;
    }

    public String getCommunityGroupInformationMessage() {
        return communityGroupInformationMessage;
    }

    public void setCommunityGroupInformationMessage(String communityGroupInformationMessage) {
        this.communityGroupInformationMessage = communityGroupInformationMessage;
    }

    public int getAdminProfileIcon() {
        return adminProfileIcon;
    }

    public void setAdminProfileIcon(int adminProfileIcon) {
        this.adminProfileIcon = adminProfileIcon;
    }

    public int getInformationIcon() {
        return informationIcon;
    }

    public void setInformationIcon(int informationIcon) {
        this.informationIcon = informationIcon;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

}