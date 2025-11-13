/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryPerson extends User {

    private final String deliveryPersonID;
    private final List<Order> assignedOrders = new ArrayList<>();
    private final List<Order> pastOrders = new ArrayList<>();
    private DeliveryVehicle deliveryVehicle;


    // Constructor for new delivery person
    public DeliveryPerson(String username, String password, String name,
                          String email, String phone, String address) {
        super(username, password, name, email, phone, address);
        this.deliveryPersonID = "DRV-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    // @return the driver's unique ID
    public String getID() {
        return deliveryPersonID;
    }


    // @return list of active orders
    public List<Order> getAssignedOrders() {
        return new ArrayList<>(assignedOrders);
    }

    // @return list of past orders
    public List<Order> getPastOrders() {
        return new ArrayList<>(pastOrders);
    }

    // @return the assigned delivery vehicle
    public DeliveryVehicle getDeliveryVehicle() {
        return deliveryVehicle;
    }

    /**
     * Assigns a new order to the driver.
     * @param order the order to assign
     */
    public void assignOrder(Order order) {
        if (order != null) {
            assignedOrders.add(order);
        }
    }
    
    /**
     * Marks an assigned order as completed.
     * @param orderID the ID of the order to complete
     */
    public void completeOrder(String orderID) {
        Order found = null;
        for (Order o : assignedOrders) {
            if (o.getOrderID().equals(orderID)) {
                found = o;
                break;
            }
        }

        if (found != null) {
            assignedOrders.remove(found);
            pastOrders.add(found);
            System.out.println("Order " + orderID + " delivered by " + name);
        }
    }

    // Displays all currently assigned orders.
    public void displayAssignedOrders() {
        for (Order o : assignedOrders) {
            o.displayDetails();
        }
    }

    // Displays all completed orders.
    public void displayPastOrders() {
        for (Order o : pastOrders) {
            o.displayDetails();
        }
    }
}
