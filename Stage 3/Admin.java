/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Admin extends User {

    // Instance variables.
    private final Set<Customer> managedCustomers = new HashSet<>();
    private final Set<Restaurant> managedRestaurants = new HashSet<>();
    private final Set<DeliveryPerson> managedDeliveryPeople = new HashSet<>();
    private final Set<DeliveryVehicle> managedVehicles = new HashSet<>();
    private final List<Order> listOrders = new ArrayList<>();
    private final List<Payment> listPayments = new ArrayList<>();

    /**
     * This constructor initializes the instance variables.
     * @param username
     * @param password
     * @param name
     * @param email
     * @param phone
     * @param address
     */
    public Admin(String username, String password, String name, String email, String phone, String address) {
        super(username, password, name, email, phone, address);
    }

    //  -------------------- Customer Management --------------------

    /**
     * This method adds a customer to the list of customers.
     * @param c
     */
    public void addCustomer(Customer c) {
        managedCustomers.add(c);
    }

    /**
     * This method updates a customer's information.
     * @param customerID
     * @param newPhone
     */
    public void updateCustomer(String customerID, String newPhone) {
        Customer c = findCustomerByID(customerID);
        if (c != null) c.setPhone(newPhone);
    }

    /**
     * This method removes a customer from the list of customers.
     * @param customerID
     */
    public void removeCustomer(String customerID) {
        managedCustomers.removeIf(c -> c.getCustomerID().equals(customerID));
    }

    /**
     * This method finds a customer by their ID.
     * @param customerID
     * @return
     */
    public Customer findCustomerByID(String customerID) {
        for (Customer c : managedCustomers) {
            if (c.getCustomerID().equals(customerID)) {
                return c;
            }
        }
        return null;
    }

    //  -------------------- Restaurant Management --------------------

    /**
     * This method adds a restaurant to the list of restaurants.
     * @param r
     */
    public void addRestaurant(Restaurant r) {
        managedRestaurants.add(r);
    }

    /**
     * This method updates a restaurant's information.
     * @param restaurantID
     * @param newPhone
     */
    public void updateRestaurant(String restaurantID, String newPhone) {
        for (Restaurant r : managedRestaurants) {
            if (r.getID().equals(restaurantID)) {
            }
        }
    }

    /**
     * This method removes a restaurant from the list of restaurants.
     * @param restaurantID
     */
    public void removeRestaurant(String restaurantID) {
        managedRestaurants.removeIf(r -> r.getID().equals(restaurantID));
    }

    //  -------------------- Delivery Person Management --------------------

    /**
     * This method adds a delivery person to the list of delivery people.
     * @param d
     */
    public void addDeliveryPerson(DeliveryPerson d) {
        managedDeliveryPeople.add(d);
    }

    /**
     * This method updates a delivery person's information.
     * @param deliveryPersonID
     * @param newPhone
     */
    public void updateDeliveryPerson(String deliveryPersonID, String newPhone) {
        for (DeliveryPerson d : managedDeliveryPeople) {
            if (d.getID().equals(deliveryPersonID)) {
                d.setPhone(newPhone);
            }
        }
    }

    /**
     * This method removes a delivery person from the list of delivery people.
     * @param deliveryPersonID
     */
    public void removeDeliveryPerson(String deliveryPersonID) {
        managedDeliveryPeople.removeIf(d -> d.getID().equals(deliveryPersonID));
    }

    //  -------------------- Vehicle Management --------------------

    /**
     * This method adds a delivery vehicle to the list of vehicles.
     * @param v
     */
    public void addDeliveryVehicle(DeliveryVehicle v) {
        managedVehicles.add(v);
    }

    /**
     * This method updates a delivery vehicle's information.
     * @param vehicleName
     * @param newColor
     */
    public void updateDeliveryVehicle(String vehicleName, String newColor) {
        for (DeliveryVehicle v : managedVehicles) {
            if (v.getName().equalsIgnoreCase(vehicleName)) {
                v.setColor(newColor);
            }
        }
    }

    /**
     * This method removes a delivery vehicle from the list of vehicles.
     * @param vehicleName
     */
    public void removeDeliveryVehicle(String vehicleName) {
        managedVehicles.removeIf(v -> v.getName().equalsIgnoreCase(vehicleName));
    }
    
    //  -------------------- Order & Payment Tracking --------------------

    /**
     * This method adds an order to the list of orders.
     * @param o
     */
    public void recordOrder(Order o) {
        if (o != null) listOrders.add(o);
    }

    /**
     * This method adds a payment to the list of payments.
     * @param p
     */
    public void recordPayment(Payment p) {
        if (p != null) listPayments.add(p);
    }

    /**
     * This method displays all listed orders.
     */
    public void displayOrders() {
        for (Order o : listOrders) {
            o.displayDetails();
        }
    }

    /**
     * This method displays all listed payments.
     */
    public void displayPayments() {
        for (Payment p : listPayments) {
            p.displayDetails();
        }
    }

    /**
     * This method displays menus of all managed restaurants.
     */
    public void displayMenus() {
        for (Restaurant r : managedRestaurants) {
            System.out.println("Menu for " + r.getName());
            if (r.getMenu() != null) {
                r.getMenu().displayCompleteMenu();
            }
        }
    }

    /**
     * This method returns the list of customers.
     * @return
     */
    public Set<Customer> customers() {
        return managedCustomers;
    }

    /**
     * This method returns the list of restaurants.
     * @return
     */
    public Set<Restaurant> restaurants() {
        return managedRestaurants;
    }

    /**
     * This method returns the list of delivery people.
     * @return
     */
    public Set<DeliveryPerson> deliveryPeople() {
        return managedDeliveryPeople;
    }

    /**
     * This method returns the list of delivery vehicles.
     * @return
     */
    public Set<DeliveryVehicle> vehicles() {
        return managedVehicles;
    }
}
