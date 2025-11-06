/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.*;

public class DeliveryPerson {
    private String deliveryPersonID;
    private ArrayList<Order> assignedOrders = new ArrayList<>();
    private ArrayList<Order> pastOrders = new ArrayList<>();
    private DeliveryVehicle deliveryVehicle;

    public DeliveryPerson(ArrayList<Order> assignedOrders, ArrayList<Order> pastOrders,
                          DeliveryVehicle assignedVehicle) {
        if (assignedOrders != null) 
            this.assignedOrders.addAll(assignedOrders);
        
        if (pastOrders != null) 
            this.pastOrders.addAll(pastOrders);
        
        this.deliveryVehicle = assignedVehicle;
        this.deliveryPersonID = UUID.randomUUID().toString();
    }

    public DeliveryPerson(DeliveryVehicle assignedVehicle) {
        this(null, null, assignedVehicle);
    }

    public String getID() 
        { return deliveryPersonID; 
    }

    public void assignOrder(String orderID) {
        Order placeholder = new Order(new String[0][0], null, null, this, new HashSet<>());
        placeholder.setOrderID(orderID);
        assignedOrders.add(placeholder);
    }

    public void completeOrder(String orderID) {
        Iterator<Order> it = assignedOrders.iterator();
        while (it.hasNext()) {
            Order o = it.next();
            if (o != null && orderID != null && orderID.equals(o.getOrderID())) {
                it.remove();
                pastOrders.add(o);
                break;
            }
        }
    }

    public ArrayList<Order> getAssignedOrders() 
        { return assignedOrders; 
    }
    
    public void displayAssignedOrders() {
        System.out.println("Assigned orders" + deliveryPersonID + ":");
        for (Order o : assignedOrders) o.displayDetails();
    }

    public ArrayList<Order> getPastOrders() 
        { return pastOrders; 
    }
    
    public void displayPastOrders() {
        System.out.println("Past orders" + deliveryPersonID + ":");
        for (Order o : pastOrders) o.displayDetails();
    }

    public DeliveryVehicle getDeliveryVehicles()
        { return deliveryVehicle; 
    }
    
    public void assignDeliveryVehicle(DeliveryVehicle vehicle) 
        { this.deliveryVehicle = vehicle;
    }
}
