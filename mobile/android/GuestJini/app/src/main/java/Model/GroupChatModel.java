package Model;

public class GroupChatModel {
    private String addGroupChatDateAndTime;
    private String addGroupChatMemberName;
    private String addGroupChatMessage;
    private int viewType;
    public static final int ONE_TYPE = 1;
    public static final int TWO_TYPE = 2;

    public GroupChatModel(String addGroupChatDateAndTime, String addGroupChatMemberName, String addGroupChatMessage, int viewType) {
        this.addGroupChatDateAndTime = addGroupChatDateAndTime;
        this.addGroupChatMemberName = addGroupChatMemberName;
        this.addGroupChatMessage = addGroupChatMessage;
        this.viewType = viewType;
    }

    public String getAddGroupChatDateAndTime() {
        return addGroupChatDateAndTime;
    }

    public void setAddGroupChatDateAndTime(String addGroupChatDateAndTime) {
        this.addGroupChatDateAndTime = addGroupChatDateAndTime;
    }

    public String getAddGroupChatMemberName() {
        return addGroupChatMemberName;
    }

    public void setAddGroupChatMemberName(String addGroupChatMemberName) {
        this.addGroupChatMemberName = addGroupChatMemberName;
    }

    public String getAddGroupChatMessage() {
        return addGroupChatMessage;
    }

    public void setAddGroupChatMessage(String addGroupChatMessage) {
        this.addGroupChatMessage = addGroupChatMessage;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
