/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Admin extends User {

    private final Set<Customer> managedCustomers = new HashSet<>();
    private final Set<Restaurant> managedRestaurants = new HashSet<>();
    private final Set<DeliveryPerson> managedDeliveryPeople = new HashSet<>();
    private final Set<DeliveryVehicle> managedVehicles = new HashSet<>();
    private final List<Order> listOrders = new ArrayList<>();
    private final List<Payment> listPayments = new ArrayList<>();

    public Admin(String username, String password, String name, String email, String phone, String address) {
        super(username, password, name, email, phone, address);
    }


    public void addCustomer(Customer c) {
        managedCustomers.add(c);
    }

    public void updateCustomer(String customerID, String newPhone) {
        Customer c = findCustomerByID(customerID);
        if (c != null) c.setPhone(newPhone);
    }

    public void removeCustomer(String customerID) {
        managedCustomers.removeIf(c -> c.getCustomerID().equals(customerID));
    }

    public Customer findCustomerByID(String customerID) {
        for (Customer c : managedCustomers) {
            if (c.getCustomerID().equals(customerID)) {
                return c;
            }
        }
        return null;
    }

    public void addRestaurant(Restaurant r) {
        managedRestaurants.add(r);
    }

    public void updateRestaurant(String restaurantID, String newPhone) {
        for (Restaurant r : managedRestaurants) {
            if (r.getID().equals(restaurantID)) {
            }
        }
    }

    public void removeRestaurant(String restaurantID) {
        managedRestaurants.removeIf(r -> r.getID().equals(restaurantID));
    }

    public void addDeliveryPerson(DeliveryPerson d) {
        managedDeliveryPeople.add(d);
    }

    public void updateDeliveryPerson(String deliveryPersonID, String newPhone) {
        for (DeliveryPerson d : managedDeliveryPeople) {
            if (d.getID().equals(deliveryPersonID)) {
                d.setPhone(newPhone);
            }
        }
    }

    public void removeDeliveryPerson(String deliveryPersonID) {
        managedDeliveryPeople.removeIf(d -> d.getID().equals(deliveryPersonID));
    }

    public void addDeliveryVehicle(DeliveryVehicle v) {
        managedVehicles.add(v);
    }

    public void updateDeliveryVehicle(String vehicleName, String newColor) {
        for (DeliveryVehicle v : managedVehicles) {
            if (v.getName().equalsIgnoreCase(vehicleName)) {
                v.setColor(newColor);
            }
        }
    }

    public void removeDeliveryVehicle(String vehicleName) {
        managedVehicles.removeIf(v -> v.getName().equalsIgnoreCase(vehicleName));
    }

    public void recordOrder(Order o) {
        if (o != null) listOrders.add(o);
    }

    public void recordPayment(Payment p) {
        if (p != null) listPayments.add(p);
    }

    public void displayOrders() {
        for (Order o : listOrders) {
            o.displayDetails();
        }
    }

    public void displayPayments() {
        for (Payment p : listPayments) {
            p.displayDetails();
        }
    }

    public void displayMenus() {
        for (Restaurant r : managedRestaurants) {
            System.out.println("Menu for " + r.getName());
            if (r.getMenu() != null) {
                r.getMenu().displayCompleteMenu();
            }
        }
    }

    public Set<Customer> customers() {
        return managedCustomers;
    }

    public Set<Restaurant> restaurants() {
        return managedRestaurants;
    }

    public Set<DeliveryPerson> deliveryPeople() {
        return managedDeliveryPeople;
    }

    public Set<DeliveryVehicle> vehicles() {
        return managedVehicles;
    }
}
