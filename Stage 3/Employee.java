/**
 * Author: 
 * Assignment: Project
 */
import java.util.Set;

public class Employee extends User {


    public Employee(String username, String password, String name,
                    String email, String phone, String address) {
        super(username, password, name, email, phone, address);
    }


    public void displayCustomers(Set<Customer> customers) {
        System.out.println("== Customers ==");
        for (Customer c : customers) {
            System.out.println("  - " + c.getCustomerID() + " (" + c.getName() + ")");
        }
    }

    public void displayRestaurants(Set<Restaurant> restaurants) {
        System.out.println("== Restaurants ==");
        for (Restaurant r : restaurants) {
            System.out.println("  - " + r.getID() + " :: " + r.getName());
        }
    }

    public void displayDeliveryPersons(Set<DeliveryPerson> drivers) {
        System.out.println("== Delivery People ==");
        for (DeliveryPerson d : drivers) {
            System.out.println("  - " + d.getID() + " (" + d.getName() + ")");
        }
    }

    public void displayDeliveryVehicles(Set<DeliveryVehicle> vehicles) {
        System.out.println("== Delivery Vehicles ==");
        for (DeliveryVehicle v : vehicles) {
            System.out.println("  - " + v.getName() + " " + v.getMake() + " " + v.getModel());
        }
    }
}
