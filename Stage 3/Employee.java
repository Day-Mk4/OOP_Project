/**
 * Author: 
 * Assignment: Project
 */
import java.util.Set;

public class Employee extends User {

    // Constructor to create a new employee user.
    public Employee(String username, String password, String name,
                    String email, String phone, String address) {
        super(username, password, name, email, phone, address);
    }

    // Displays all customers and their IDs.
    public void displayCustomers(Set<Customer> customers) {
        System.out.println("== Customers ==");
        for (Customer c : customers) {
            System.out.println("  - " + c.getCustomerID() + " (" + c.getName() + ")");
        }
    }

    // Displays all restaurants and IDs.
    public void displayRestaurants(Set<Restaurant> restaurants) {
        System.out.println("== Restaurants ==");
        for (Restaurant r : restaurants) {
            System.out.println("  - " + r.getID() + " :: " + r.getName());
        }
    }

    // Displays all delivery people and IDs.
    public void displayDeliveryPersons(Set<DeliveryPerson> drivers) {
        System.out.println("== Delivery People ==");
        for (DeliveryPerson d : drivers) {
            System.out.println("  - " + d.getID() + " (" + d.getName() + ")");
        }
    }

    // Displays all delivery vehicles with names and models.
    public void displayDeliveryVehicles(Set<DeliveryVehicle> vehicles) {
        System.out.println("== Delivery Vehicles ==");
        for (DeliveryVehicle v : vehicles) {
            System.out.println("  - " + v.getName() + " " + v.getMake() + " " + v.getModel());
        }
    }
}
