package Model;

public class AttachFilesModel {
    private String attachFilesName;
    private String attachFilesSize;
    private Integer deleteIcon;

    public String getAttachFilesName() {
        return attachFilesName;
    }

    public void setAttachFilesName(String attachFilesName) {
        this.attachFilesName = attachFilesName;
    }

    public String getAttachFilesSize() {
        return attachFilesSize;
    }

    public void setAttachFilesSize(String attachFilesSize) {
        this.attachFilesSize = attachFilesSize;
    }

    public Integer getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(Integer deleteIcon) {
        this.deleteIcon = deleteIcon;
    }
}
