package Model;

public class PaymentOptionsModel {
    private int paymentOptionsIcon;
    private String paymentOptionsName;

//    public PaymentOptionsModel(int paymentOptionsIcon, String paymentOptionsName) {
//        this.paymentOptionsIcon = paymentOptionsIcon;
//        this.paymentOptionsName = paymentOptionsName;
//    }

    public int getPaymentOptionsIcon() {
        return paymentOptionsIcon;
    }

    public void setPaymentOptionsIcon(int paymentOptionsIcon) {
        this.paymentOptionsIcon = paymentOptionsIcon;
    }

    public String getPaymentOptionsName() {
        return paymentOptionsName;
    }

    public void setPaymentOptionsName(String paymentOptionsName) {
        this.paymentOptionsName = paymentOptionsName;
    }
}
