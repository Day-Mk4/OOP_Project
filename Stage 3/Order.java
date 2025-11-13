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


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String id) {
   
    }

    public List<String[]> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public void removeOrderItems(String itemName) {
    for (int i = 0; i < orderItems.size(); i++) {
        if (orderItems.get(i)[0].equalsIgnoreCase(itemName)) {
            orderItems.remove(i);
            i--; 
        }
    }
    price = calculatePrice();
    }


    public double getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getCustomer() {
        return customer.getCustomerID();
    }

    public Customer getCustomerObj() {
        return customer;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson dp) {
        this.deliveryPerson = dp;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


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

    public void displayDetails() {
        System.out.println("Order " + orderID + " @ " + restaurant.getName() + " for " + customer.getName());

        for (String[] row : orderItems) {
            System.out.println("  - " + row[0] + " x" + row[2] + " @ $" + row[1]);
        }

        System.out.println("Total: $" + getPrice());
    }
}
