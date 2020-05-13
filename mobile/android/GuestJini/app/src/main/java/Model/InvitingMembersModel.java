package Model;

import com.carmel.guestjini.Models.User.AddressBook;
import com.carmel.guestjini.R;

public class InvitingMembersModel {
    private String personId;
    private String userId;
    private String groupId;
    private int profilePicture;
    private String profileName;
    private String profileGender;
    private int favouritesIndicator;
    private String inviteButton;
    private String invitationSentMessage;
    private String invitationSentDate;
    private int isInvited;
    private int hasAcceptedInvitation;

    public InvitingMembersModel() {
    }

    public InvitingMembersModel(AddressBook addressBook, String groupId) {
        this.personId = addressBook.getId();
        this.profilePicture = R.drawable.profile;
        this.profileName = addressBook.getDisplayName();
        this.isInvited = addressBook.getIsInvited();
        this.hasAcceptedInvitation = addressBook.getHasAcceptedInvitation();
        this.userId = addressBook.getUserId();
        this.groupId = groupId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileGender() {
        return profileGender;
    }

    public void setProfileGender(String profileGender) {
        this.profileGender = profileGender;
    }

    public int getFavouritesIndicator() {
        return favouritesIndicator;
    }

    public void setFavouritesIndicator(int favouritesIndicator) {
        this.favouritesIndicator = favouritesIndicator;
    }

    public String getInviteButton() {
        return inviteButton;
    }

    public void setInviteButton(String inviteButton) {
        this.inviteButton = inviteButton;
    }

    public String getInvitationSentMessage() {
        return invitationSentMessage;
    }

    public void setInvitationSentMessage(String invitationSentMessage) {
        this.invitationSentMessage = invitationSentMessage;
    }

    public String getInvitationSentDate() {
        return invitationSentDate;
    }

    public void setInvitationSentDate(String invitationSentDate) {
        this.invitationSentDate = invitationSentDate;
    }

    public int getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(int isInvited) {
        this.isInvited = isInvited;
    }

    public int getHasAcceptedInvitation() {
        return hasAcceptedInvitation;
    }

    public void setHasAcceptedInvitation(int hasAcceptedInvitation) {
        this.hasAcceptedInvitation = hasAcceptedInvitation;
    }
}
