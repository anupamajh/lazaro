package Model;

public class InterestGroupsModel {
    public InterestGroupsModel(String addInterestCategoryTitle, String addInterestGroupTitle, String addInterestGroupDescription, String addIndicatorDescription, int addIndicator, int addInformationIcons, int viewType) {
        this.addInterestCategoryTitle = addInterestCategoryTitle;
        this.addInterestGroupTitle = addInterestGroupTitle;
        this.addInterestGroupDescription = addInterestGroupDescription;
        this.addIndicatorDescription = addIndicatorDescription;
        this.addIndicator = addIndicator;
        this.addInformationIcons = addInformationIcons;
        this.viewType = viewType;
    }

    String addInterestCategoryTitle;
     String addInterestGroupTitle;
     String addInterestGroupDescription;
     String addIndicatorDescription;
     int addIndicator;
     int addInformationIcons;
    private int viewType;
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }


    public static final int ONE_TYPE = 1;
    public static final int TWO_TYPE = 2;
    public static final int THIRD_TYPE = 3;
    public static final int FOURTH_TYPE = 4;

//    public InterestGroupsModel(String addInterestCategoryTitle, String addInterestGroupTitle, String addInterestGroupDescription, String addIndicatorDescription, int addIndicator, int addInformationIcons) {
//        this.addInterestCategoryTitle = addInterestCategoryTitle;
//        this.addInterestGroupTitle = addInterestGroupTitle;
//        this.addInterestGroupDescription = addInterestGroupDescription;
//        this.addIndicatorDescription = addIndicatorDescription;
//        this.addIndicator = addIndicator;
//        this.addInformationIcons = addInformationIcons;
//    }

    public String getAddInterestCategoryTitle() {
        return addInterestCategoryTitle;
    }

    public void setAddInterestCategoryTitle(String addInterestCategoryTitle) {
        this.addInterestCategoryTitle = addInterestCategoryTitle;
    }

    public String getAddInterestGroupTitle() {
        return addInterestGroupTitle;
    }

    public void setAddInterestGroupTitle(String addInterestGroupTitle) {
        this.addInterestGroupTitle = addInterestGroupTitle;
    }

    public String getAddInterestGroupDescription() {
        return addInterestGroupDescription;
    }

    public void setAddInterestGroupDescription(String addInterestGroupDescription) {
        this.addInterestGroupDescription = addInterestGroupDescription;
    }

    public String getAddIndicatorDescription() {
        return addIndicatorDescription;
    }

    public void setAddIndicatorDescription(String addIndicatorDescription) {
        this.addIndicatorDescription = addIndicatorDescription;
    }

    public int getAddIndicator() {
        return addIndicator;
    }

    public void setAddIndicator(int addIndicator) {
        this.addIndicator = addIndicator;
    }

    public int getAddInformationIcons() {
        return addInformationIcons;
    }

    public void setAddInformationIcons(int addInformationIcons) {
        this.addInformationIcons = addInformationIcons;
    }
}
