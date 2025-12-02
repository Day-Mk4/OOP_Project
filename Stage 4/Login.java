/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

import java.util.Set;

public class Login {

    private final String username;
    private final String password;
    private final Set<Customer> listCustomers;
    private final Set<DeliveryPerson> listDrivers;
    private final Admin admin;

    // Constuctor for login
    public Login(String username, String password,
                 Set<Customer> listCustomers,
                 Set<DeliveryPerson> listDrivers,
                 Admin admin) {
        this.username = username;
        this.password = password;
        this.listCustomers = listCustomers;
        this.listDrivers = listDrivers;
        this.admin = admin;
    }

    /**
     * Checks whether the entered credentials match the Admin user.
     * @return true if admin login is successful
     */
    public boolean authenticateAsAdmin() {
        return admin != null
                && admin.getUsername().equals(username)
                && admin.getPassword().equals(password);
    }

    /**
     * Checks credentials against all customers.
     * @return the matching Customer
     */
    public Customer authenticateAsCustomer() {
        for (Customer c : listCustomers) {
            if (c.getUsername().equals(username)
                    && c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Checks credentials against all delivery drivers.
     * @return the matching DeliveryPerson
     */
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
