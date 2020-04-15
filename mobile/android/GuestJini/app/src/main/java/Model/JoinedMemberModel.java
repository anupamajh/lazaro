package Model;

public class JoinedMemberModel {
    private int joinedProfilePicture;
    private String joinedMembersName;
    private String joinedDate;

    public JoinedMemberModel(int joinedProfilePicture, String joinedMembersName, String joinedDate) {
        this.joinedProfilePicture = joinedProfilePicture;
        this.joinedMembersName = joinedMembersName;
        this.joinedDate = joinedDate;
    }

    public int getJoinedProfilePicture() {
        return joinedProfilePicture;
    }

    public void setJoinedProfilePicture(int joinedProfilePicture) {
        this.joinedProfilePicture = joinedProfilePicture;
    }

    public String getJoinedMembersName() {
        return joinedMembersName;
    }

    public void setJoinedMembersName(String joinedMembersName) {
        this.joinedMembersName = joinedMembersName;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }
}
