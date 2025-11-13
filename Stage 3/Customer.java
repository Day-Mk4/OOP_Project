/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private final String customerID;
    private final List<Order> orderHistory = new ArrayList<>();

    // Constructor for customer
    public Customer(String username, String password, String name, String email,
                    String phone, String address, String customerID) {
        super(username, password, name, email, phone, address);
        this.customerID = customerID;
    }
    
    //@return the customer's unique ID 
    public String getCustomerID() {
        return customerID;
    }
    
    /**
     * Returns a copy of the customer's order history.
     * @return list of past orders
     */
    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }

    //  Displays all orders in the customer's order history.
    public void displayOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No past orders.");
        } else {
            for (Order o : orderHistory) {
                o.displayDetails();
            }
        }
    }

    /**
     * Records a new order into the customer's order history.
     * @param order the order to add
     */
    public void recordOrder(Order order) {
        if (order != null) {
            orderHistory.add(order);
        }
    }

    //  display order history.
    public void viewOrders() {
        displayOrderHistory();
    }
}
