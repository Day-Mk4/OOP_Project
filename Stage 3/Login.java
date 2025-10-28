/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticateAsAdmin() {
        return username.equalsIgnoreCase("admin") && password.equals("admin123");
    }

    public boolean authenticateAsCustomer() {
        return username.equalsIgnoreCase("customer") && password.equals("cust123");
    }

    public boolean authenticateAsDeliveryPerson() {
        return username.equalsIgnoreCase("driver") && password.equals("drive123");
    }
}
