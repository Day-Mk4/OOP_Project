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

    // Constructor to creates a new payment record with a generated ID.
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

    // @return unique payment ID
    public String getPaymentID() {
        return paymentID;
    }

    // Set PaymentID
    public void setPaymentID(String paymentID) {
        
    }

    // @return payment amount
    public double getPrice() {
        return price;
    }

    // Updates the payment amount.
    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    // @return delivery person.
    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    // @return order
    public Order getOrder() {
        return order;
    }

    // @return customer
    public Customer getCustomer() {
        return customer;
    }

    // @return payment status
    public boolean getPaidStatus() {
        return paidStatus;
    }

    // Updates the paid/unpaid status. 
    public void updatePaidStatus(boolean newPaidStatus) {
        this.paidStatus = newPaidStatus;
    }

    // Prints a summary of payment details.
    public void displayDetails() {
        System.out.println(
            "Payment " + paymentID +
            " for Order " + order.getOrderID() +
            ", Amount: $" + price +
            ", Paid: " + paidStatus
        );
    }
}
