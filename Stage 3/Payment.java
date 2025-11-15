/**
 * Author: 
 * Assignment: Project
 */
import java.util.Set;
import java.util.UUID;

public class Payment {

    // Instance variables
    private final String paymentID;
    private double price;
    private final DeliveryPerson deliveryPerson;
    private final Order order;
    private final Customer customer;
    private boolean paidStatus;
    private final Set<DiscountCoupon> appliedCoupons;

    /**
     * This constructor initializes the instance variables.
     * @param price
     * @param deliveryPerson
     * @param order
     * @param customer
     * @param appliedCoupons
     */
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

    /**
     * This method returns the payment ID.
     * @return
     */
    public String getPaymentID() {
        return paymentID;
    }

    /**
     * This method returns the price.
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method updates the price.
     * @param newPrice
     */
    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * This method returns the delivery person.
     * @return
     */
    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    /**
     * This method returns the order.
     * @return
     */
    public Order getOrder() {
        return order;
    }

    /**
     * This method returns the customer.
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * This method returns the payment status.
     * @return
     */
    public boolean getPaidStatus() {
        return paidStatus;
    }

    /**
     * This method updates the payment status.
     * @param newPaidStatus
     */
    public void updatePaidStatus(boolean newPaidStatus) {
        this.paidStatus = newPaidStatus;
    }

    /**
     * This method displays the payment details.
     */
    public void displayDetails() {
        System.out.println(
            "Payment " + paymentID +
            " for Order " + order.getOrderID() +
            ", Amount: $" + price +
            ", Paid: " + paidStatus
        );
    }
}
