package Model;

import com.carmel.guestjini.Models.User.AddressBook;
import com.carmel.guestjini.R;

public class InterestGroupMembersModel {

    public String groupMemberProfileName;
    public String groupMemberProfileCreatedDate;
    private int groupProfileIcon;
    private String groupId;

    public InterestGroupMembersModel() {
    }

    public InterestGroupMembersModel(AddressBook addressBook, String groupId) {
        this.groupMemberProfileName = addressBook.getDisplayName();
        this.groupMemberProfileCreatedDate = addressBook.getCreationTime();
        this.groupProfileIcon = R.drawable.profile;
        this.groupId = groupId;
    }

    public String getGroupMemberProfileName() {
        return groupMemberProfileName;
    }

    public void setGroupMemberProfileName(String groupMemberProfileName) {
        this.groupMemberProfileName = groupMemberProfileName;
    }

    public String getGroupMemberProfileCreatedDate() {
        return groupMemberProfileCreatedDate;
    }

    public void setGroupMemberProfileCreatedDate(String groupMemberProfileCreatedDate) {
        this.groupMemberProfileCreatedDate = groupMemberProfileCreatedDate;
    }

    public int getGroupProfileIcon() {
        return groupProfileIcon;
    }

    public void setGroupProfileIcon(int groupProfileIcon) {
        this.groupProfileIcon = groupProfileIcon;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
