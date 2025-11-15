/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Order {

    // Instance variables
    private final String orderID;
    private final List<String[]> orderItems = new ArrayList<>(); 
    private double price;
    private final Restaurant restaurant;
    private final Customer customer;
    private DeliveryPerson deliveryPerson;
    private Payment payment;
    private final Set<DiscountCoupon> appliedCoupons;

    /**
     * The constructor initializes the instance variables.
     * @param items
     * @param restaurant
     * @param customer
     * @param deliveryPerson
     * @param coupons
     */
    public Order(String[][] items, Restaurant restaurant, Customer customer,
                 DeliveryPerson deliveryPerson, Set<DiscountCoupon> coupons) {

        this.orderID = "ORD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        if (items != null) {
            for (String[] row : items) {
                orderItems.add(row);
            }
        }

        this.restaurant = restaurant;
        this.customer = customer;
        this.deliveryPerson = deliveryPerson;
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
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * This method returns the customer.
     * @return
     */
    public String getCustomer() {
        return customer.getCustomerID();
    }

    /**
     * This method returns the customer object.
     * @return
     */
    public Customer getCustomerObj() {
        return customer;
    }

    /**
     * This method returns the delivery person.
     * @return
     */
    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    /**
     * This method sets the delivery person.
     * @param dp
     */
    public void setDeliveryPerson(DeliveryPerson dp) {
        this.deliveryPerson = dp;
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

    /**
     * This method calculates the price and applies discounts if any.
     * @return
     */
    public double calculatePrice() {
        double sum = 0.0;

        for (String[] row : orderItems) {
            double p = Double.parseDouble(row[1]);
            int q = Integer.parseInt(row[2]);
            sum += p * q;
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
        System.out.println("Order " + orderID + " @ " + restaurant.getName() + " for " + customer.getName());

        for (String[] row : orderItems) {
            System.out.println("  - " + row[0] + " x" + row[2] + " @ $" + row[1]);
        }

        System.out.println("Total: $" + getPrice());
    }
}
