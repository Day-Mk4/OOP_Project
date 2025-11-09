/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryPerson extends User {

    private final String deliveryPersonID;
    private List<Payment> driverPayments;
    private List<Order> assignedOrders = new ArrayList<>();
    private List<Order> pastOrders = new ArrayList<>();
    private DeliveryVehicle deliveryVehicle;


    public DeliveryPerson(String username, String password, String name,
                          String email, String phone, String address, List<Payment> newListPayments, List<Order> newAssignedOrders, List<Order> newPastOrders) {
        super(username, password, name, email, phone, address);
        driverPayments = newListPayments;
        assignedOrders = newAssignedOrders;
        pastOrders = newPastOrders;
        this.deliveryPersonID = "DRV-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }


    public String getID() {
        return deliveryPersonID;
    }

    public List<Order> getAssignedOrders() {
        return assignedOrders;
    }

    public List<Order> getPastOrders() {
        return pastOrders;
    }

    public List<Payment> getDriverPayments() {
        return driverPayments;
    }

    public DeliveryVehicle getDeliveryVehicle() {
        return deliveryVehicle;
    }

    public void assignDeliveryVehicle(DeliveryVehicle vehicle) {
        this.deliveryVehicle = vehicle;
    }

    public void assignOrder(Order order) {
        if (order != null) {
            assignedOrders.add(order);
        }
    }
    
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

    public void displayAssignedOrders() {
        for (Order o : assignedOrders) {
            o.displayDetails();
        }
    }

    public void displayPastOrders() {
        for (Order o : pastOrders) {
            o.displayDetails();
        }
    }
}
