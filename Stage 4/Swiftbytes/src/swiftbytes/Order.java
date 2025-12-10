/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
package swiftbytes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Order {

    // Instance variables
    private String driverID;
    private Payment payment;
    private double price;
    private final String orderID;
    private final List<String[]> orderItems = new ArrayList<>(); 
    private final String restaurantName;
    private final String customerName;
    private final Set<DiscountCoupon> appliedCoupons;

    /**
     * The constructor initializes the instance variables.
     * @param items
     * @param restaurant
     * @param customer
     * @param deliveryPerson
     * @param coupons
     */
    public Order(String orderID, String[][] items, String restaurantName, String customerName,
                 String driverID, Set<DiscountCoupon> coupons) {

        if (items != null) {
            for (String[] row : items) {
                orderItems.add(row);
            }
        }
        this.orderID = orderID;
        this.restaurantName = restaurantName;
        this.customerName = customerName;
        this.driverID = driverID;
        this.appliedCoupons = coupons;
        this.price = calculatePrice();
    }

    /**
     * This method returns the order ID.
     * @return
     */
    public String getOrderID() {
        return orderID;
    }
    
    /**
     * This method returns the order items.
     * @return
     */
    public List<String[]> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    /**
     * This method removes an item from the order.
     * @param itemName
     */
    public void removeOrderItems(String itemName) {
    for (int i = 0; i < orderItems.size(); i++) {
        if (orderItems.get(i)[0].equalsIgnoreCase(itemName)) {
            orderItems.remove(i);
            i--; 
        }
    }
    price = calculatePrice();
    }

    /**
     * This method returns the price.
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method returns the restaurant.
     * @return
     */
    public String getRestaurant() {
        return restaurantName;
    }

    /**
     * This method returns the customer.
     * @return
     */
    public String getCustomer() {
        return customerName;
    }

    /**
     * This method returns the customer object.
     * @return
     */
    public String getCustomerObj() {
        return customerName;
    }

    /**
     * This method returns the delivery person.
     * @return
     */
    public String getDeliveryPerson() {
        return driverID;
    }

    /**
     * This method sets the delivery person.
     * @param dp
     */
    public void setDeliveryPerson(DeliveryPerson dp) {
        this.driverID = dp.getID();
    }

    /**
     * This method returns the payment.
     * @return
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * This method sets the payment.
     * @param payment
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public Set<DiscountCoupon> getAppliedCoupons()
    {
        return appliedCoupons;
    }

    /**
     * This method calculates the price and applies discounts if any.
     * @return
     */
    public double calculatePrice() {
        double sum = 0.0;

        for (String[] row : orderItems) {
            double p = Double.parseDouble(row[1]);
            sum += p;
        }

        double discounted = sum;
        if (appliedCoupons != null) {
            for (DiscountCoupon c : appliedCoupons) {
                discounted = c.applyDiscount(discounted);
            }
        }

        return Math.round(discounted * 100.0) / 100.0;
    }

    /**
     * This method displays the order details.
     */
    public void displayDetails() {
        System.out.println("Order " + orderID + " @ " + restaurantName + " for " + customerName);

        for (String[] row : orderItems) {
            System.out.println("  - " + row[0] + " @ $" + row[1]);
        }

        System.out.println("Total: $" + getPrice());
    }
}
