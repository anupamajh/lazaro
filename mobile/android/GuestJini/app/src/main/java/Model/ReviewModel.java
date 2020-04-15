package Model;

public class ReviewModel {
    private String reviewerName;
    private String reviewDate;
    private String reviewDescription;
    private int reviewerProfile;

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public int getReviewerProfile() {
        return reviewerProfile;
    }

    public void setReviewerProfile(int reviewerProfile) {
        this.reviewerProfile = reviewerProfile;
    }
}
