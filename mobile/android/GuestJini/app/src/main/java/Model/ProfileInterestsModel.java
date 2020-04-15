package Model;

public class ProfileInterestsModel {
    private String addMainCategory;
    private int upwardDropDownButton;

    public ProfileInterestsModel(String addMainCategory, int upwardDropDownButton) {
        this.addMainCategory = addMainCategory;
        this.upwardDropDownButton = upwardDropDownButton;
    }

    public String getAddMainCategory() {
        return addMainCategory;
    }

    public void setAddMainCategory(String addMainCategory) {
        this.addMainCategory = addMainCategory;
    }

    public int getUpwardDropDownButton() {
        return upwardDropDownButton;
    }

    public void setUpwardDropDownButton(int upwardDropDownButton) {
        this.upwardDropDownButton = upwardDropDownButton;
    }
}
