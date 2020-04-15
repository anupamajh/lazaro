package Model;

public class RentInvoiceModel {
    private String rentInvoiceDate;
    private String rentInvoiceNo;
    private String rentInvoiceAmount;
    private int rightNavigationIcon;

    public RentInvoiceModel(String rentInvoiceDate, String rentInvoiceNo, String rentInvoiceAmount, int rightNavigationIcon) {
        this.rentInvoiceDate = rentInvoiceDate;
        this.rentInvoiceNo = rentInvoiceNo;
        this.rentInvoiceAmount = rentInvoiceAmount;
        this.rightNavigationIcon = rightNavigationIcon;
    }
    public String getRentInvoiceDate() {
        return rentInvoiceDate;
    }

    public void setRentInvoiceDate(String rentInvoiceDate) {
        this.rentInvoiceDate = rentInvoiceDate;
    }

    public String getRentInvoiceNo() {
        return rentInvoiceNo;
    }

    public void setRentInvoiceNo(String rentInvoiceNo) {
        this.rentInvoiceNo = rentInvoiceNo;
    }

    public String getRentInvoiceAmount() {
        return rentInvoiceAmount;
    }

    public void setRentInvoiceAmount(String rentInvoiceAmount) {
        this.rentInvoiceAmount = rentInvoiceAmount;
    }

    public int getRightNavigationIcon() {
        return rightNavigationIcon;
    }

    public void setRightNavigationIcon(int rightNavigationIcon) {
        this.rightNavigationIcon = rightNavigationIcon;
    }
}
