/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private final String customerID;
    private final List<Order> orderHistory = new ArrayList<>();


    public Customer(String username, String password, String name, String email,
                    String phone, String address, String customerID) {
        super(username, password, name, email, phone, address);
        this.customerID = customerID;
    }


    public String getCustomerID() {
        return customerID;
    }
    
    

    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }


    public void displayOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No past orders.");
        } else {
            for (Order o : orderHistory) {
                o.displayDetails();
            }
        }
    }

    public void recordOrder(Order order) {
        if (order != null) {
            orderHistory.add(order);
        }
    }


    public void viewOrders() {
        displayOrderHistory();
    }
}
