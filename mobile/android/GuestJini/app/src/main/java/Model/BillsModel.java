package Model;

public class BillsModel {
    private String billsDate;
    private String billsNo;
    private String productName;
    private String productAmount;
    private int rightNavigationIcon;

    public BillsModel(String billsDate, String billsNo, String productName, String productAmount, int rightNavigationIcon) {
        this.billsDate = billsDate;
        this.billsNo = billsNo;
        this.productName = productName;
        this.productAmount = productAmount;
        this.rightNavigationIcon = rightNavigationIcon;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getBillsNo() {
        return billsNo;
    }

    public void setBillsNo(String billsNo) {
        this.billsNo = billsNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public int getRightNavigationIcon() {
        return rightNavigationIcon;
    }

    public void setRightNavigationIcon(int rightNavigationIcon) {
        this.rightNavigationIcon = rightNavigationIcon;
    }
}
