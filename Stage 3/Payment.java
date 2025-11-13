/**
 * Author: 
 * Assignment: Project
 */
import java.util.Set;
import java.util.UUID;

public class Payment {

    private final String paymentID;
    private double price;
    private final DeliveryPerson deliveryPerson;
    private final Order order;
    private final Customer customer;
    private boolean paidStatus;
    private final Set<DiscountCoupon> appliedCoupons;

    public Payment(double price, DeliveryPerson deliveryPerson, Order order,
                   Customer customer, Set<DiscountCoupon> appliedCoupons) {
        this.paymentID = "PAY-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.price = price;
        this.deliveryPerson = deliveryPerson;
        this.order = order;
        this.customer = customer;
        this.paidStatus = false;
        this.appliedCoupons = appliedCoupons;
    }


    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public Order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean getPaidStatus() {
        return paidStatus;
    }

    public void updatePaidStatus(boolean newPaidStatus) {
        this.paidStatus = newPaidStatus;
    }

    public void displayDetails() {
        System.out.println(
            "Payment " + paymentID +
            " for Order " + order.getOrderID() +
            ", Amount: $" + price +
            ", Paid: " + paidStatus
        );
    }
}
