/**
 * Author: 
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Order {

    private final String orderID;
    private final List<String[]> orderItems = new ArrayList<>(); 
    private double price;
    private final Restaurant restaurant;
    private final Customer customer;
    private DeliveryPerson deliveryPerson;
    private Payment payment;
    private final Set<DiscountCoupon> appliedCoupons;

    // Constructor to create a new order with generated ID and calculates price.
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

    //@return the unique order ID
    public String getOrderID() {
        return orderID;
    }

    // Set OrderID
    public void setOrderID(String id) {
   
    }

    // @return a copy of the items in the order 
    public List<String[]> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    // Removes all matching items and recalculates price.
    public void removeOrderItems(String itemName) {
    for (int i = 0; i < orderItems.size(); i++) {
        if (orderItems.get(i)[0].equalsIgnoreCase(itemName)) {
            orderItems.remove(i);
            i--; 
        }
    }
    price = calculatePrice();
    }


    // @return the price
    public double getPrice() {
        return price;
    }

    // @return the restaurant
    public Restaurant getRestaurant() {
        return restaurant;
    }

    // @return the customer id
    public String getCustomer() {
        return customer.getCustomerID();
    }

    // @return the customer
    public Customer getCustomerObj() {
        return customer;
    }

    // @return the delivery person
    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    // Sets delivery person
    public void setDeliveryPerson(DeliveryPerson dp) {
        this.deliveryPerson = dp;
    }

    // @return the payment
    public Payment getPayment() {
        return payment;
    }

    // Set payment
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    // Calculates total price with applied coupons.
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

    // Prints all order details.
    public void displayDetails() {
        System.out.println("Order " + orderID + " @ " + restaurant.getName() + " for " + customer.getName());

        for (String[] row : orderItems) {
            System.out.println("  - " + row[0] + " x" + row[2] + " @ $" + row[1]);
        }

        System.out.println("Total: $" + getPrice());
    }
}
