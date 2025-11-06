/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.Set;

public class Login {

    private final String username;
    private final String password;
    private final Set<Employee> listEmployees;
    private final Set<Customer> listCustomers;
    private final Set<DeliveryPerson> listDrivers;
    private final Admin admin;

    
    public Login(String username, String password,
                 Set<Employee> listEmployees,
                 Set<Customer> listCustomers,
                 Set<DeliveryPerson> listDrivers,
                 Admin admin) {
        this.username = username;
        this.password = password;
        this.listEmployees = listEmployees;
        this.listCustomers = listCustomers;
        this.listDrivers = listDrivers;
        this.admin = admin;
    }

    public boolean authenticateAsAdmin() {
        return admin != null
                && admin.getUsername().equals(username)
                && admin.getPassword().equals(password);
    }

    public Customer authenticateAsCustomer() {
        for (Customer c : listCustomers) {
            if (c.getUsername().equals(username)
                    && c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }

    public DeliveryPerson authenticateAsDeliveryPerson() {
        for (DeliveryPerson d : listDrivers) {
            if (d.getUsername().equals(username)
                    && d.getPassword().equals(password)) {
                return d;
            }
        }
        return null;
    }
}
