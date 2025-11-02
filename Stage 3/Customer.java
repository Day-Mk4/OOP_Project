/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;

public class Customer {
    private String customerID;
    private ArrayList<Order> orderHistory = new ArrayList<>();

    public Customer(String customerID, ArrayList<Order> orderHistory) {
        this.customerID = customerID;
        if (orderHistory != null) this.orderHistory.addAll(orderHistory);
    }

    public ArrayList<Order> getOrderHistory() 
        { return orderHistory; 
    }

    public void displayOrderHistory() {
        System.out.println("Order history for customer " + customerID + ":");
        for (Order o : orderHistory) o.displayDetails();
    }

    public void placeOrder(Order order) {
        if (order != null) orderHistory.add(order);
    }

    public void addToOrder(Order order) {
        if (order != null) System.out.println("Added items to order " + order.getOrderID());
    }

    public void payBill(Order order) {
        if (order != null && order.getPayment() != null) {
            order.getPayment().updatePaidStatus(true);
        }
    }

    public void viewOrders()
        { displayOrderHistory();
    }

    public String getCustomerID()
        { return customerID; 
    }
}
