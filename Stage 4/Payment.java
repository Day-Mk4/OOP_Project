/**
 * Author: 
 * Assignment: Project
 */
package swiftbytes;

public class Payment {

    // Instance variables
    private final String paymentID;
    private double price;
    private final String deliveryPersonName;
    private final String orderID;
    private final String customerName;
    private boolean paidStatus;

    /**
     * This constructor initializes the instance variables.
     * @param price
     * @param deliveryPerson
     * @param order
     * @param customer
     * @param appliedCoupons
     */
    public Payment(String paymentID, double price, String deliveryPerson, String orderID,
                   String customer, boolean status) {
        this.paymentID = paymentID;
        this.price = price;
        this.deliveryPersonName = deliveryPerson;
        this.orderID = "ORD-"+orderID;
        this.customerName = customer;
        this.paidStatus = status;
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
    public String getDeliveryPerson() {
        return deliveryPersonName;
    }

    /**
     * This method returns the order.
     * @return
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * This method returns the customer.
     * @return
     */
    public String getCustomer() {
        return customerName;
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
            " for Order " + orderID +
            ", Amount: $" + price +
            ", Paid: " + paidStatus
        );
    }
}
