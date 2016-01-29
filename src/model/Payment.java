package model;

/**
 * This class is for payment. Client can pay for rent, repair or other.
 * Created by Nataliia Kozoriz on 24.01.2016.
 *
 * @see PaymentName
 * @see CarOrder
 */
public class Payment {

    /**
     * Payment number
     */
    private int id;

    /**
     * Number of order which this payment is connected with
     */
    private int carOrder;

    /**
     * Payment price
     */
    private double price;

    /**
     * Payment name
     */
    private PaymentName paymentName;

    /**
     * True, if this payment is paid.
     */
    private boolean paid;

    public Payment(int carOrder, double price, PaymentName paymentName) {
        this.carOrder = carOrder;
        this.price = price;
        this.paymentName = paymentName;
        paid = false;
    }

    public Payment(int id, int carOrder, double price, PaymentName paymentName, boolean paid) {
        this.id = id;
        this.carOrder = carOrder;
        this.price = price;
        this.paymentName = paymentName;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarOrder() {
        return carOrder;
    }

    public void setCarOrder(int carOrder) {
        this.carOrder = carOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PaymentName getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(PaymentName paymentName) {
        this.paymentName = paymentName;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
