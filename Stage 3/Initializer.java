/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
import java.util.*;

public class Initializer {

    private final Set<Employee> listEmployees = new HashSet<>();
    private final Set<Customer> listCustomers = new HashSet<>();
    private final Set<Restaurant> listRestaurants = new HashSet<>();
    private final Set<DeliveryVehicle> listVehicles = new HashSet<>();
    private final Set<DiscountCoupon> listCoupons = new HashSet<>();
    private final Set<DeliveryPerson> listDrivers = new HashSet<>();
    private final List<Order> listOrders = new ArrayList<>();
    private final List<Payment> listPayments = new ArrayList<>();
    private final List<Menu> listMenus = new ArrayList<>();

    // Default admin user
    private final Admin admin = new Admin(
        "admin", "admin123", "System Admin", "admin@swift.com", "555-0000", "ADM"
    );

    private final Scanner sc = new Scanner(System.in);

    // Starts the program and loads all generic data.
    public void Run() {
        createGenericEmployees();
        createGenericCustomers();
        createGenericRestaurantsAndMenus();
        createGenericDriversAndVehicles();
        createGenericCoupons();
        System.out.println("=== SwiftBytes ===");
        loginMenu();
    }

    // Creates one default employee.
    private void createGenericEmployees() {
        listEmployees.add(new Employee("emp1", "pw", "Emily Employee", "e@swift.com", "555-5555", "EMP"));
    }

    // Creates two sample customers
    private void createGenericCustomers() {
        listCustomers.add(new Customer("day", "pw", "Amadeo Pena", "day@yahoo.com", "555-2222", "NM", "CUST-001"));
        listCustomers.add(new Customer("lazo", "pw", "Lazo McCarrol", "lazo@gmail.com", "123-1234", "NM", "CUST-002"));
    }

    // Creates restaurants and menus, then registers them with Admin.
    private void createGenericRestaurantsAndMenus() {
        Restaurant r1 = new Restaurant("R-101", "China King", "301 W Main St., ABQ", "(505)489-0363", 3.6);
        Restaurant r2 = new Restaurant("R-102", "June's Country Restaurant", "445 Outskirts Rd., ABQ", "(505)935-8876", 4.0);
        Restaurant r3 = new Restaurant("R-103", "McDonald's", "345 E Main St., ABQ", "(505)606-7521", 2.5);
        Restaurant r4 = new Restaurant("R-104", "Olive Garden", "113 W Ramada St., ABQ", "(505)112-3345", 3.4);
        Restaurant r5 = new Restaurant("R-105", "Saheel's Authentic Indian Food", "678 Wedding St., ABQ", "(505)698-0034", 4.6);

        Menu m1 = new Menu(r1);
        m1.addSingleItem("Soda", 3.00);
        m1.addSingleItem("Fried Rice", 3.00);
        m1.addSingleItem("3 Eggrolls", 3.00);
        m1.addSingleItem("6 Dumplings", 3.50);
        m1.addSingleItem("Chow Mein", 8.00);
        m1.addSingleItem("Sweet and Sour Pork", 10.00);
        m1.addSingleItem("Kung Pao Chicken", 15.00);
        r1.setMenu(m1);

        Menu m2 = new Menu(r2);
        m2.addSingleItem("Soda", 3.00);
        m2.addSingleItem("Sweet Potato Fries", 3.00);
        m2.addSingleItem("Coleslaw", 3.00);
        m2.addSingleItem("Jalapeno Poppers", 3.50);
        m2.addSingleItem("Pulled Pork Sandwich", 8.00);
        m2.addSingleItem("Mushroom Burger", 10.00);
        m2.addSingleItem("Chicken Fried Steak", 15.00);
        r2.setMenu(m2);

        Menu m3 = new Menu(r3);
        m3.addSingleItem("Fries + Drink", 5.99);
        m3.addSingleItem("Burger", 9.99);
        m3.addComboItem("Combo", 13.99);
        r3.setMenu(m3);

        Menu m4 = new Menu(r4);
        m4.addSingleItem("Drink + side", 7.00);
        m4.addSingleItem("Spaghetti", 8.00);
        m4.addComboItem("Steak", 15.00);
        r4.setMenu(m4);

        listRestaurants.addAll(Arrays.asList(r1, r2, r3, r4, r5));
        listMenus.addAll(Arrays.asList(m1, m2, m3, m4));

        admin.addRestaurant(r1);
        admin.addRestaurant(r2);
        admin.addRestaurant(r3);
        admin.addRestaurant(r4);
        admin.addRestaurant(r5);
    }

    // Creates sample delivery drivers and vehicles
    private void createGenericDriversAndVehicles() {
        DeliveryPerson d1 = new DeliveryPerson("driver1", "pw", "John", "d1@swift.com", "222-2222", "NM");
        DeliveryPerson d2 = new DeliveryPerson("driver2", "pw", "Doe", "d2@swift.com", "333-3333", "NM");

        listDrivers.addAll(Arrays.asList(d1, d2));

        DeliveryVehicle v1 = new DeliveryVehicle("Scooter", "Honda", "Ruckus", "2022", "Red");
        DeliveryVehicle v2 = new DeliveryVehicle("Car", "Toyota", "Corolla", "2019", "Blue");

        v1.setAssignedDriver(d1);
        v2.setAssignedDriver(d2);

        listVehicles.addAll(Arrays.asList(v1, v2));

        admin.addDeliveryPerson(d1);
        admin.addDeliveryPerson(d2);
        admin.addDeliveryVehicle(v1);
        admin.addDeliveryVehicle(v2);
    }

    // Creates several discount coupons.
    private void createGenericCoupons() {
        listCoupons.add(new DiscountCoupon("WELCOME10", 0.10, true));
        listCoupons.add(new DiscountCoupon("SAVE5", 5.00, true));
        listCoupons.add(new DiscountCoupon("23XC2444", 3.00, false));
        listCoupons.add(new DiscountCoupon("5X6734RT", 3.00, true));
        listCoupons.add(new DiscountCoupon("7YTU8922", 5.00, true));
        listCoupons.add(new DiscountCoupon("4FLKKM23", 10.00, true));
    }

    // Handles login attempts for admin, customer, and delivery driver.
    public void loginMenu() {
        while (true) {
            System.out.println("\nLogin as: 1) Admin  2) Customer  3) Delivery  0) Exit");
            String choice = sc.nextLine().trim();
            if ("0".equals(choice)) break;

            System.out.print("Username: ");
            String u = sc.nextLine().trim();
            System.out.print("Password: ");
            String p = sc.nextLine().trim();

            Login login = new Login(u, p, listEmployees, listCustomers, listDrivers, admin);

            switch (choice) {
                case "1" -> {
                    if (login.authenticateAsAdmin())
                        new AdminMenu(
                            admin,
                            listCustomers,
                            listRestaurants,
                            listDrivers,
                            listVehicles,
                            listCoupons, 
                            listOrders,
                            listPayments,
                            sc
                        ).run();
                    else System.out.println("Invalid admin credentials.");
                }
                case "2" -> {
                    Customer cust = login.authenticateAsCustomer();
                    if (cust != null)
                        new CustomerMenu(cust, listRestaurants, listCoupons, listDrivers, listOrders, listPayments, admin, sc).run();
                    else System.out.println("Invalid customer credentials.");
                }
                case "3" -> {
                    DeliveryPerson dp = login.authenticateAsDeliveryPerson();
                    if (dp != null)
                        new DriverMenu(dp, sc).run();
                    else System.out.println("Invalid driver credentials.");
                }
                default -> System.out.println("Unknown choice.");
            }
        }
        System.out.println("Goodbye!");
    }
}
