/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
public class Main {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        SubMenuManager app = new SubMenuManager(initializer.createGenericEmployees(), initializer.createGenericCustomers(), initializer.createGenericMenus(), initializer.createGenericRestaurants(), initializer.createGenericVehicles(), initializer.createGenericCoupons());
        app.loginMenu();
    }
}
